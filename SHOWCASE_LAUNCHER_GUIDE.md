# Debug Showcase Launcher - Implementation Guide

## 🎯 Problem Solved

You need a way to access the Design System Showcase in debug builds without:
- ❌ Blocking app launch with a launcher screen
- ❌ Disturbing normal app flow
- ❌ Adding complexity to navigation
- ❌ Being visible in production

## ✅ Solution: Multiple Non-Intrusive Approaches

---

## 🚀 Approach 1: Floating Action Button (RECOMMENDED)

**Best for: Mobile (Android/iOS) and Web**

### What You Get
- 🎨 Small FAB in bottom-right corner
- 👻 Appears only in debug builds
- 🚫 Zero code in release builds
- ⚡ One tap to open showcase

### Implementation

```kotlin
// In your main App.kt
@Composable
fun App() {
    InnovrexTheme {
        Box(Modifier.fillMaxSize()) {
            // Your main app content
            YourMainScreen()
            
            // Debug-only FAB (automatically hidden in release)
            DebugShowcaseLauncher()
        }
    }
}
```

### Screenshot Preview
```
┌─────────────────────────────┐
│                             │
│   Your App Content          │
│                             │
│                             │
│                             │
│                             │
│                      ┌────┐ │
│                      │ 🎨 │ │ ← FAB (debug only)
│                      └────┘ │
└─────────────────────────────┘
```

### Features
- ✅ **Expandable** - Tap to show "Design System Showcase" label
- ✅ **Animated** - Smooth fade in/out transitions
- ✅ **Transparent** - Semi-transparent to not obscure content
- ✅ **Dismissible** - Easy to close and return to app

---

## 🎯 Approach 2: Simple FAB (Even Less Intrusive)

**Best for: Minimal UI disruption**

### Implementation

```kotlin
@Composable
fun App() {
    InnovrexTheme {
        Box(Modifier.fillMaxSize()) {
            YourMainScreen()
            
            // Simpler version without expand animation
            DebugShowcaseLauncherSimple()
        }
    }
}
```

### Features
- ✅ **Minimal** - Just the emoji icon
- ✅ **70% opacity** - Even less intrusive
- ✅ **One tap** - Opens showcase immediately

---

## ⌨️ Approach 3: Keyboard Shortcut

**Best for: Desktop/Web power users**

### Implementation

```kotlin
@Composable
fun App() {
    var showShowcase by remember { mutableStateOf(false) }
    
    InnovrexTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .debugShowcaseShortcut { showShowcase = true }
        ) {
            if (showShowcase) {
                DesignSystemShowcase(onClose = { showShowcase = false })
            } else {
                YourMainScreen()
            }
        }
    }
}
```

### Shortcut
- **Windows/Linux**: `Ctrl + Shift + K`
- **Mac**: `Cmd + Shift + K`

---

## 🖥️ Approach 4: Desktop Menu Bar

**Best for: Desktop applications**

### Implementation (Desktop only)

```kotlin
fun main() = application {
    var showShowcase by remember { mutableStateOf(false) }
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "Innovrex"
    ) {
        // Add debug menu to menu bar
        DebugMenuBar(onOpenShowcase = { showShowcase = true })
        
        App()
        
        if (showShowcase) {
            ShowcaseWindow(onCloseRequest = { showShowcase = false })
        }
    }
}
```

### Features
- ✅ **Native menu** - Appears in app menu bar
- ✅ **Keyboard shortcut** - Also supports Ctrl+K
- ✅ **Separate window** - Showcase in dedicated window

---

## 📱 Platform-Specific Recommendations

### Android
```kotlin
@Composable
fun AndroidApp() {
    Box {
        Navigation() // Your nav graph
        DebugShowcaseLauncher() // FAB overlay
    }
}
```

**Alternative: Add to Debug Drawer**
```kotlin
if (IS_DEBUG) {
    NavigationDrawerItem(
        label = { Text("🎨 Design System") },
        onClick = { openShowcase() }
    )
}
```

### iOS
```kotlin
@Composable
fun IOSApp() {
    Box {
        Navigation()
        DebugShowcaseLauncher() // FAB overlay
    }
}
```

**Alternative: Add Gesture**
```kotlin
// 3-finger tap to open showcase
.pointerInput(Unit) {
    detectTapGestures(
        onPress = { /* detect 3 fingers */ }
    )
}
```

### Desktop
```kotlin
fun main() = application {
    Window(...) {
        DebugMenuBar(...)  // Menu bar item
        App()
    }
    ShowcaseWindow()  // Separate window
}
```

### Web
```kotlin
@Composable
fun WebApp() {
    Box(
        modifier = Modifier.debugShowcaseShortcut { ... }
    ) {
        Navigation()
        DebugShowcaseLauncher() // FAB overlay
    }
}
```

---

## 🔧 Advanced: Custom Launcher

### Option A: Shake to Open (Android)

```kotlin
// In AndroidMain
@Composable
fun ShakeDetector(onShake: () -> Unit) {
    val context = LocalContext.current
    val sensorManager = context.getSystemService<SensorManager>()
    
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val acceleration = event.values[0]
                if (acceleration > SHAKE_THRESHOLD) {
                    onShake()
                }
            }
        }
        // Register sensor...
        onDispose { /* unregister */ }
    }
}
```

### Option B: Long Press Icon (Any Platform)

```kotlin
Image(
    painter = painterResource("logo.png"),
    contentDescription = null,
    modifier = Modifier
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    if (IS_DEBUG) openShowcase()
                }
            )
        }
)
```

### Option C: Developer Settings Screen

```kotlin
@Composable
fun SettingsScreen() {
    // ... other settings ...
    
    if (IS_DEBUG) {
        SettingsItem(
            title = "Design System Showcase",
            onClick = { openShowcase() }
        )
    }
}
```

---

## 💡 Comparison Matrix

| Approach | Intrusiveness | Platforms | Discovery | Setup |
|----------|---------------|-----------|-----------|-------|
| **FAB** | Low | All | Medium | Easy |
| **Keyboard Shortcut** | None | Desktop/Web | Low | Easy |
| **Menu Bar** | None | Desktop | High | Easy |
| **Launcher Screen** | High | All | High | Medium |
| **Shake Gesture** | None | Mobile | Low | Complex |
| **Settings Screen** | None | All | Medium | Medium |

---

## 📋 Recommended Setup by Project Type

### Consumer App (Mobile-first)
```kotlin
Box {
    NavigationGraph()
    DebugShowcaseLauncherSimple() // Minimal FAB
}
```

### Enterprise App (Desktop-first)
```kotlin
Window(...) {
    DebugMenuBar(...) // Menu item
    App()
}
ShowcaseWindow() // Separate window
```

### Web App
```kotlin
Box(
    modifier = Modifier.debugShowcaseShortcut { ... }
) {
    Content()
    DebugShowcaseLauncher() // FAB + keyboard
}
```

### Multi-platform Library
```kotlin
// Provide both options
Box {
    Content()
    DebugShowcaseLauncher() // For mobile/web
}
// + DebugMenuBar for desktop
```

---

## ✅ Final Recommendation

**Use the Floating Action Button approach** because:

1. ✅ **Non-intrusive** - Doesn't block app launch
2. ✅ **Always accessible** - Available from any screen
3. ✅ **Platform-agnostic** - Works everywhere
4. ✅ **Zero production overhead** - Completely removed in release
5. ✅ **User-friendly** - Obvious what it does (🎨 icon)
6. ✅ **Simple integration** - One line of code

### Quick Start

```kotlin
// That's it! Just add this one composable
@Composable
fun App() {
    InnovrexTheme {
        Box {
            YourContent()
            DebugShowcaseLauncher() // ← Add this
        }
    }
}
```

---

## 🎨 Customization

### Change FAB Position
```kotlin
DebugShowcaseLauncher(
    modifier = Modifier
        .align(Alignment.BottomStart) // Left side instead
        .padding(16.dp)
)
```

### Change FAB Icon
Edit `DebugShowcaseLauncher.kt`:
```kotlin
Text("🎨")  // Change to any emoji or Icon
```

### Change FAB Color
```kotlin
FloatingActionButton(
    containerColor = Color.Red.copy(alpha = 0.7f), // Custom color
    // ...
)
```

---

## 🚀 Ready to Use!

Your showcase is now accessible without disrupting the app flow! The FAB will:
- ✅ Appear in debug builds only
- ✅ Stay out of the way
- ✅ Open showcase with one tap
- ✅ Be completely removed in release

**No launcher screen needed!** 🎉

