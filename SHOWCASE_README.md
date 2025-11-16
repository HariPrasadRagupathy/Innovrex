# Design System Showcase Module

## 📚 Overview

The **Showcase Module** is a debug-only catalog/storybook for the Innovrex Design System. It provides:

- 🎨 **Interactive component preview** - See all design system components
- 🎛️ **Live property controls** - Adjust variants, sizes, states in real-time
- 📖 **Token reference** - Visual showcase of colors, spacing, typography
- 🐛 **Debug-only** - Automatically disabled in release builds
- 🌍 **Multiplatform** - Works on Android, iOS, Web, Desktop

## 🏗️ Architecture

```
showcase/
├── DesignSystemShowcase.kt    # Entry point (debug-gated)
├── BuildConfig.kt              # Platform-specific debug flags
├── ShowcaseUI.kt               # Main showcase UI layout
├── ComponentRegistry.kt        # Component catalog registry
├── components/                 # Component showcases
│   └── RIButtonShowcase.kt    # Button with interactive controls
└── tokens/                     # Token showcases
    ├── ColorTokensShowcase.kt
    └── SpacingTokensShowcase.kt
```

## 🚀 Usage

### Android
```kotlin
// In your MainActivity or navigation
@Composable
fun App() {
    // Only accessible in debug builds
    if (BuildConfig.DEBUG) {
        Button(onClick = { navigateToShowcase() }) {
            Text("Open Design System Showcase")
        }
    }
}

// Or launch directly
@Composable
fun ShowcaseScreen() {
    DesignSystemShowcase(
        onClose = { /* navigate back */ }
    )
}
```

### Desktop (JVM)
```kotlin
fun main() = application {
    // Main app window
    Window(onCloseRequest = ::exitApplication, title = "Innovrex") {
        App()
    }
    
    // Showcase in separate window (debug only)
    ShowcaseWindow(onCloseRequest = { /* optional close handler */ })
}
```

### iOS
```swift
// In your SwiftUI view
struct ContentView: View {
    var body: some View {
        #if DEBUG
        Button("Open Showcase") {
            // Present showcase
        }
        #endif
    }
}
```

### Web
```kotlin
@Composable
fun WebApp() {
    var showShowcase by remember { mutableStateOf(false) }
    
    if (showShowcase) {
        DesignSystemShowcase(onClose = { showShowcase = false })
    } else {
        MainApp()
        
        // Debug button (bottom-right corner)
        if (IS_DEBUG) {
            FloatingActionButton(
                onClick = { showShowcase = true },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(Icons.Palette, contentDescription = "Design System")
            }
        }
    }
}
```

## 🎨 Adding New Components to Showcase

### Step 1: Create Showcase File
```kotlin
// showcase/components/RITextFieldShowcase.kt
package com.hp.innovrex.showcase.components

import androidx.compose.runtime.*
import com.hp.innovrex.showcase.ComponentItem
import com.hp.innovrex.showcase.ComponentCategory

val RITextFieldShowcase = ComponentItem(
    name = "RITextField",
    description = "Text input with variants and validation states.",
    category = ComponentCategory.ATOM,
    preview = { RITextFieldPreview() },
    controls = { RITextFieldControls() }
)

@Composable
private fun RITextFieldPreview() {
    val state = remember { RITextFieldShowcaseState() }
    
    RITextField(
        value = state.value,
        onValueChange = { state.value = it },
        variant = state.variant,
        enabled = state.enabled,
        error = if (state.showError) "This field is required" else null
    )
}

@Composable
private fun RITextFieldControls() {
    val state = remember { RITextFieldShowcaseState() }
    
    Column {
        // Variant selector
        ControlSection(title = "Variant") {
            RadioButtonItem("Outlined", state.variant == Outlined) { ... }
            RadioButtonItem("Filled", state.variant == Filled) { ... }
        }
        
        // State toggles
        SwitchItem("Enabled", state.enabled) { state.enabled = it }
        SwitchItem("Show Error", state.showError) { state.showError = it }
    }
}

@Stable
private class RITextFieldShowcaseState {
    var value by mutableStateOf("")
    var variant by mutableStateOf(RITextFieldVariant.Outlined)
    var enabled by mutableStateOf(true)
    var showError by mutableStateOf(false)
}
```

### Step 2: Register in ComponentRegistry
```kotlin
// showcase/ComponentRegistry.kt
object ComponentRegistry {
    val atoms: List<ComponentItem> = listOf(
        RIButtonShowcase,
        RITextFieldShowcase,  // ← Add here
        // ...
    )
}
```

## 🎛️ Control Components Available

The showcase provides reusable control components:

### RadioButtonItem
```kotlin
RadioButtonItem(
    label = "Option Name",
    selected = state.option == OptionValue,
    onClick = { state.option = OptionValue }
)
```

### SwitchItem
```kotlin
SwitchItem(
    label = "Toggle Name",
    checked = state.enabled,
    onCheckedChange = { state.enabled = it }
)
```

### ControlSection
```kotlin
ControlSection(title = "Section Title") {
    // Radio buttons, switches, sliders, etc.
}
```

## 📋 Current Showcases

### Components
- ✅ **RIButton** - All variants, shapes, sizes, loading/disabled states

### Tokens
- ✅ **Colors** - Brand palette with hex codes
- ✅ **Spacing** - T-shirt sizing scale

### Coming Soon
- [ ] Typography tokens
- [ ] Shape tokens
- [ ] Motion/Animation previews
- [ ] Transition examples
- [ ] RITextField
- [ ] RIIcon
- [ ] RIAvatar
- [ ] RICard
- [ ] More...

## 🔒 Debug-Only Enforcement

The showcase is **automatically disabled in release builds**:

### How it works
```kotlin
// BuildConfig values per platform:
// Android: Uses generated BuildConfig.DEBUG from Gradle
// iOS: Always true in dev builds
// Desktop: Always true in dev builds
// Web: Always true in dev builds

@Composable
fun DesignSystemShowcase() {
    if (IS_DEBUG) {  // ← Only renders in debug
        ShowcaseContent()
    }
    // In release: empty composable, no overhead
}
```

### Gradle Configuration
```kotlin
// build.gradle.kts
android {
    buildTypes {
        debug {
            buildConfigField("boolean", "DEBUG", "true")
        }
        release {
            buildConfigField("boolean", "DEBUG", "false")  // ← Disabled
        }
    }
    buildFeatures {
        buildConfig = true
    }
}
```

## 🎯 Best Practices

### 1. **Keep State Isolated**
Each showcase should have its own state class:
```kotlin
@Stable
private class ComponentShowcaseState {
    var property by mutableStateOf(defaultValue)
}
```

### 2. **Use Descriptive Controls**
Make it obvious what each control does:
```kotlin
ControlSection(title = "Button Variant") {  // ✅ Clear
    RadioButtonItem("Filled", ...)
    RadioButtonItem("Outlined", ...)
}

// Not:
ControlSection(title = "Type") {  // ❌ Vague
    RadioButtonItem("A", ...)
}
```

### 3. **Add Helpful Descriptions**
```kotlin
ComponentItem(
    name = "RIButton",
    description = "Multi-variant button component with support for different " +
                  "shapes, sizes, loading states, and icons.",  // ✅ Helpful
    // ...
)
```

### 4. **Show All States**
Include toggles for all important states:
- Enabled/Disabled
- Loading
- Error
- Focus
- Hover (where applicable)

### 5. **Reset Functionality**
Always provide a reset button:
```kotlin
Button(onClick = { state.reset() }) {
    Text("Reset to Defaults")
}
```

## 📱 Platform-Specific Features

### Desktop
- **Separate window**: Showcase opens in its own window
- **Side-by-side**: Run app and showcase simultaneously

### Android
- **Debug menu**: Add showcase to debug drawer
- **Shake to open**: Implement shake detector to launch showcase

### Web
- **Floating button**: Bottom-right FAB to toggle showcase
- **Keyboard shortcut**: `Ctrl/Cmd + K` to open

### iOS
- **Debug menu**: Add to developer settings
- **Gesture**: 3-finger tap to open

## 🚧 Future Enhancements

- [ ] **Search/Filter**: Find components quickly
- [ ] **Code snippets**: Copy usage examples
- [ ] **Export**: Screenshot/save component states
- [ ] **Dark mode toggle**: Preview in light/dark
- [ ] **Accessibility audit**: WCAG compliance checker
- [ ] **Performance metrics**: Render time, recomposition count
- [ ] **Version history**: See component evolution

## 📖 Example: Complete Button Showcase

See `RIButtonShowcase.kt` for a complete example featuring:
- ✅ Live preview with state updates
- ✅ All variant options (Filled, Outlined, Tonal, Ghost, Link, Danger)
- ✅ All shape options (Rectangle, Rounded, Pill, Circle)
- ✅ All size options (Small, Medium, Large)
- ✅ State toggles (Enabled, Loading)
- ✅ Click counter to show interaction
- ✅ Reset button

## 🎓 Learning Resources

The showcase serves as:
- **Living documentation** - See components in action
- **Design reference** - Understand design decisions
- **Developer tool** - Test edge cases
- **QA tool** - Validate all states
- **Onboarding** - New developers learn the design system

---

**Status**: ✅ Production-ready showcase with RIButton + token showcases  
**Access**: Debug builds only (automatically disabled in release)  
**Platforms**: Android, iOS, Desktop, Web

