# Showcase Module Gradle Configuration

This document explains the structure and rationale behind the `showcase/build.gradle.kts` file for the reusable Kotlin Multiplatform (KMP) design system showcase library.

## Goals of This Module
- Reusable across multiple projects (Android, Desktop/JVM, Web/JS, WASM, iOS)
- Minimal, theme-agnostic design system showcase host
- Library (not application) so it can be consumed and eventually published
- Uses modern Kotlin 2.x + Compose Multiplatform DSLs (compilerOptions, hierarchy template)

---
## Top-Level Plugins
```kotlin
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
}
```
### Why
- `kotlinMultiplatform`: Enables the multi-target source set model.
- `composeMultiplatform`: Brings Compose UI artifacts to all configured targets.
- `composeCompiler`: Ensures Kotlin compiler plugin for Compose matches Kotlin version (required for Kotlin 2.x+).
- `androidLibrary`: Declares this as a reusable AAR library rather than an app (no applicationId, consumer-friendly, publishable).

---
## Kotlin Block
```kotlin
kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget { compilerOptions { jvmTarget.set(JvmTarget.JVM_11) } }
    jvm { compilerOptions { jvmTarget.set(JvmTarget.JVM_11) } }
    js(IR) { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }
    iosArm64()
    iosSimulatorArm64()
    jvmToolchain(11)
    explicitApi()
    sourceSets { ... }
}
```
### Key Decisions & Rationale
| Item | Reason |
|------|--------|
| `applyDefaultHierarchyTemplate()` | Aligns common/ platform source-set graph automatically; reduces manual wiring.
| `androidTarget` / `jvm` | Distinguish Android (AAR) & desktop (Compose Desktop) target outputs.
| `compilerOptions` DSL | Required for Kotlin 2.x (replaces deprecated `kotlinOptions`). Ensures consistent JVM bytecode.
| `js(IR)` | IR backend is the modern JS compiler; required for advanced Compose JS features.
| `wasmJs` | Experimental inclusion for future WebAssembly support of the showcase.
| `iosArm64` & `iosSimulatorArm64` | Prepares library for iOS embedding (even if not used immediately).
| `jvmToolchain(11)` | Guarantees Java 11 toolchain consistency; avoids environment drift.
| `explicitApi()` | Forces explicit visibility & return types in public API—good for libraries.

### About `explicitApi()`
If public types are missing explicit return types or visibility, build emits warnings/errors—helps maintain stable public surface for future publishing.

---
## Source Sets & Dependencies
```kotlin
sourceSets {
  val commonMain by getting {
    dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.ui)
    }
  }
  val commonTest by getting { dependencies { implementation(libs.kotlin.test) } }
  val androidMain by getting { dependencies { implementation(libs.androidx.activity.compose) } }
  val jvmMain by getting { dependencies { implementation(compose.desktop.currentOs) } }
  val iosMain by getting
  val jsMain by getting
  val wasmJsMain by getting
}
```
### Why These Dependencies
- **runtime / foundation / ui / material3**: Minimal necessary Compose building blocks for the showcase host UI.
- **activity-compose (androidMain)**: Required only if you later embed previews needing `ComponentActivity` integration (optional; safe to keep).
- **desktop.currentOs (jvmMain)**: Brings in platform-specific windowing for desktop preview scenarios.
- Other targets (iOS/JS/WASM) intentionally left empty for future extension—keeps initial footprint small.

### Potential Future Additions
| Target | Possible Dependency |
|--------|---------------------|
| iOS | `compose.foundation` already included (works); maybe platform image loading later. |
| JS/WASM | Add navigation / router or code sample copying utility libs. |
| commonMain | `kotlinx.serialization` for saving showcase state presets. |

---
## Android Block
```kotlin
android {
  namespace = "com.hp.showcase"
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    consumerProguardFiles("consumer-rules.pro")
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  publishing { singleVariant("release") { withSourcesJar(); withJavadocJar() } }
}
```
### Decisions & Rationale
| Setting | Purpose |
|---------|---------|
| `namespace` | Unique package root for generated R classes / manifest merging isolation.
| `consumerProguardFiles` | Placeholder for future keep rules if reflection or custom annotations used.
| `compileSdk`, `minSdk`, `targetSdk` | Tied to version catalog for central control, avoids hardcoded numbers.
| `publishing.singleVariant("release")` | Prepares sources & javadoc artifacts for internal/external publication.
| `JavaVersion.VERSION_11` | Matches Kotlin & Compose recommended baseline and toolchain config.

### Why No `buildFeatures { compose = true }`
Compose Multiplatform plugin manages necessary Compose wiring; enabling Android compose manually is redundant.

---
## Explicit Version & Catalog Usage
All versions (AGP, Kotlin, Compose) are centralized in `gradle/libs.versions.toml`:
- Easier upgrades
- Guarantees alignment across modules

### Version Alignment Caveat
Current catalog lists:
```
kotlin = 2.2.20
composeMultiplatform = 1.9.0
```
Ensure Compose MPP release 1.9.0 officially supports Kotlin 2.2.20. If mismatch occurs:
1. Look up JetBrains release matrix.
2. Upgrade Compose or downgrade Kotlin to a supported patch.
3. Re-run build.

---
## Publishing (Future)
Add coordinates when ready:
```kotlin
group = "com.hp.innovrex.showcase"
version = "0.1.0"
```
Then configure a repository (e.g., Maven Local or internal Nexus):
```kotlin
publishing {
  repositories { mavenLocal() }
}
```
Run:
```bash
./gradlew :showcase:publishToMavenLocal
```

### Consider Semantic Versioning
| Phase | Recommended Version |
|-------|----------------------|
| Initial dev | `0.1.0-SNAPSHOT` |
| First stable API | `1.0.0` |
| Backward-compatible additions | Minor bump |
| Breaking changes | Major bump |

---
## API Surface Recommendations
Because `explicitApi()` is enabled:
- Annotate internal-only helpers with `internal`
- Keep public data structures (`ShowcaseItem`, `ShowcaseRegistry`) stable
- Provide extension points: e.g., `ShowcaseItemGroup` or category types later

### Possible Upcoming Abstractions
- `ShowcaseTheme` to inject spacing, colors, elevations
- `ShowcaseActionBar` to host global toggles (dark mode, search)
- `ShowcasePersistence` (saving last selected component)

---
## Dark Mode / Theming Strategy
Currently: relies on consumer wrapping in its own theme or passing a flag. Consider exposing an optional parameter:
```kotlin
fun ShowcaseHost(..., colorScheme: ColorScheme = MaterialTheme.colorScheme)
```
This allows composition within existing app theme without forcing creation of a new scheme inside the host.

---
## Why Keep iOS / WASM Early
Even if not used now:
- Avoid later source-set graph refactoring
- Easier adoption when product teams want cross-platform previews
- WASM emerging: experimental try-out without commitment

If build times become a concern, they can be temporarily disabled.

---
## Common Pitfalls Avoided
| Pitfall | Mitigation |
|---------|------------|
| Deprecated `kotlinOptions` | Migrated to `compilerOptions` DSL.
| Hardcoded versions | Centralized in version catalog.
| Unclear API surface | Enabled `explicitApi()`.
| App plugin instead of library | Correct `androidLibrary` usage.
| Missing sources jar for publishing | Added `withSourcesJar()` & `withJavadocJar()`.
| Repeated Compose enable flags | Relied on MPP compose plugin (no redundancy).

---
## Extension Ideas
1. **Search & Filter**: Add an indexed map in `ShowcaseRegistry`.
2. **State Persistence**: Use `DataStore` or simple preferences in `androidMain` / file storage in `jvmMain`.
3. **Screenshot Export**: Integrate Compose `ImageBitmap` capture.
4. **Code Snippets**: Provide optional mapping from preview to snippet text source.
5. **Testing Hooks**: Expose internal test tags for automation harness.
6. **Performance Metrics**: Optional recomposition counter overlay.

---
## Minimal Example
```kotlin
val buttonShowcase = object : ShowcaseItem {
    override val id = "button"
    override val name = "Button"
    override val description = "Demonstrates primary button variants"
    @Composable override fun Preview() { /* button UI */ }
    @Composable override fun Controls() { /* interactive knobs */ }
}
ShowcaseHost(registry = ShowcaseRegistry(listOf(buttonShowcase)))
```
Wrap in your app theme for consistent look:
```kotlin
MyTheme { ShowcaseHost(registry) }
```

---
## Troubleshooting Build Issues
| Error | Action |
|-------|--------|
| Compose / Kotlin version mismatch | Align Compose MPP release with Kotlin version (check release notes). |
| Missing consumer proguard file warning | The empty `consumer-rules.pro` is already added; safe to ignore until needed. |
| iOS linking failures in early phase | Comment out iosArm64 / iosSimulatorArm64 temporarily. |
| Slow sync | Disable wasmJs until needed. |

---
## Summary
The configuration balances **portability**, **modern Kotlin practices**, and **future growth**. It intentionally keeps dependencies small while preparing for cross-platform hosting and eventual publishing.

**Next Step Suggestions:**
1. Add `group`/`version` and test local publish.
2. Move dark mode & collapse logic into this module as optional feature flags.
3. Provide a sample consumer integration module (e.g., `showcase-sample`).
4. Add integration tests validating registry selection logic.

---
Maintained by: Innovrex Design System Team
Version of doc: 1.0
Last updated: (update when changes happen)

