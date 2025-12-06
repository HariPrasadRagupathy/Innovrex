# Quick Start Guide - Responsive Hero Section

## 🚀 Run the Application

### Option 1: Web Development Server (Recommended)

```bash
cd /Volumes/files/innovrex
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

The app will automatically open in your default browser at `http://localhost:8080`

### Option 2: Production Build

```bash
./gradlew :composeApp:wasmJsBrowserProductionWebpack
```

Output files will be in: `build/dist/wasmJs/productionExecutable/`

### Option 3: Desktop (JVM)

```bash
./gradlew :composeApp:run
```

### Option 4: Android

```bash
./gradlew :composeApp:installDebug
```

## 🎨 What You'll See

### Hero Section Features:

1. **Dark Background**: Deep black (#0A0A0F) with subtle gradient
2. **Red Accents**: Decorative radial gradients in the background
3. **Large Headline**: "Kotlin Multiplatform Software Solutions"
4. **Subtitle**: "One codebase, multiple platforms..."
5. **Two Buttons**:
   - Red "Explore Our Solutions" button
   - White outlined "Contact Us" button

### Responsive Behavior:

- **Mobile (< 768px)**: Buttons stack vertically, smaller text
- **Tablet (768px - 1024px)**: Medium sizing
- **Desktop (> 1024px)**: Buttons side-by-side, large text

## 🧪 Testing Responsiveness

### In Browser DevTools:

1. Open the app in Chrome/Firefox/Safari
2. Press `F12` to open Developer Tools
3. Click the device toolbar icon (or press `Ctrl+Shift+M`)
4. Try these preset devices:
   - iPhone 14 Pro (393px)
   - iPad Pro (1024px)
   - Desktop (1920px)

### Manual Width Testing:

1. Open Responsive Design Mode
2. Enter custom widths:
   - 375px (iPhone SE)
   - 768px (iPad Portrait)
   - 1280px (MacBook Pro)
   - 1920px (Full HD Desktop)

## 🛠️ Customization

### Change Colors

Edit: `composeApp/src/commonMain/kotlin/com/hp/innovrex/designsystem/tokens/foundation/ColorTokens.kt`

```kotlin
object BrandColors {
    val Red600 = Color(0xFFE53935) // Change this
}
```

### Change Text

Edit: `composeApp/src/commonMain/kotlin/com/hp/innovrex/features/home/ui/components/HeroSection.kt`

```kotlin
Text(
    text = "Your Custom Headline Here",
    // ...
)
```

### Adjust Breakpoints

Edit: `composeApp/src/commonMain/kotlin/com/hp/innovrex/designsystem/tokens/foundation/BreakpointTokens.kt`

```kotlin
object BreakpointTokens {
    val Desktop: Dp = 1024.dp // Adjust this
}
```

## 📂 Key Files to Know

| File | Purpose |
|------|---------|
| `App.kt` | Main app entry point |
| `HomeScreen.kt` | Home page wrapper with responsive logic |
| `HeroSection.kt` | Hero section component |
| `ColorTokens.kt` | Brand colors and theme |
| `Button.kt` | Reusable button components |
| `InnovrexTheme.kt` | Material 3 theme setup |

## 🐛 Troubleshooting

### Build Fails

```bash
# Clean and rebuild
./gradlew clean
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

### Port Already in Use

```bash
# Kill process on port 8080
lsof -ti:8080 | xargs kill -9
# Or use a different port
./gradlew :composeApp:wasmJsBrowserDevelopmentRun --args="--port=8081"
```

### Changes Not Reflecting

1. Hard refresh: `Cmd+Shift+R` (Mac) or `Ctrl+Shift+R` (Windows)
2. Clear browser cache
3. Restart the Gradle daemon: `./gradlew --stop`

## 📱 Platform Support

| Platform | Status | Command |
|----------|--------|---------|
| **Web (Wasm)** | ✅ Primary | `wasmJsBrowserDevelopmentRun` |
| **Desktop (JVM)** | ✅ Supported | `run` |
| **Android** | ✅ Supported | `installDebug` |
| **iOS** | ⚠️ Requires Xcode | Open `iosApp.xcodeproj` |

## 🎯 What's Included

- ✅ Complete design system with tokens
- ✅ Responsive hero section
- ✅ Red-based color scheme
- ✅ Material 3 theming
- ✅ Light/Dark mode support
- ✅ MVI-compatible architecture
- ✅ Clean code separation
- ✅ Production-ready components

## 📖 Learn More

- See `docs/HERO_SECTION_README.md` for detailed documentation
- See `docs/IMPLEMENTATION_SUMMARY.md` for technical overview
- Check `composeApp/src/commonMain/kotlin/com/hp/innovrex/` for source code

## 🎉 Next Steps

1. **Run the app** to see it in action
2. **Test responsive layouts** at different widths
3. **Customize colors** to match your brand
4. **Add more sections** below the hero
5. **Deploy** to your hosting provider

---

**Happy Coding! 🚀**

For questions or issues, check the implementation files or create an issue in your repository.

