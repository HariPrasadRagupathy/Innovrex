# Showcase Button Troubleshooting Guide

## Issue: Can't See the Showcase Button

### Quick Fixes Applied ✅

1. **Hardcoded IS_DEBUG to true** - No longer depends on build configuration
2. **Added TEMPORARY test FAB** - Bright pink button at bottom-right (offset 80dp)
3. **Added instructions** - Main screen now shows how to access showcase

---

## What You Should See Now

```
┌─────────────────────────────────────┐
│  Innovrex              [TopAppBar]  │
├─────────────────────────────────────┤
│                                     │
│     Welcome to Innovrex             │
│                                     │
│   Your main app content goes here   │
│                                     │
│   Design System Showcase:           │
│   • Tap 🎨 in bottom-right          │
│   • Or click button below           │
│                                     │
│                                     │
│                              ┌────┐ │
│                              │ 🎨 │ │ ← Debug Launcher FAB
│                       ┌────┐ └────┘ │
│                       │ 🎨 │        │ ← TEMP Test FAB (pink)
│                       └────┘        │
└─────────────────────────────────────┘
```

**You should see TWO 🎨 buttons in the bottom-right corner:**
1. One closer to the edge (DebugShowcaseLauncher)
2. One slightly higher/left (Temporary test button - pink/primary color)

---

## If You Still Don't See Buttons

### Check 1: Is AppWithShowcase being called?

Update your `App.kt` to use `AppWithShowcase`:

```kotlin
@Composable
fun App() {
    AppWithShowcase()  // ← Make sure this is called
}
```

### Check 2: Rebuild the project

```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

### Check 3: Check console for errors

Look for any Compose-related errors in the console output.

### Check 4: Verify IS_DEBUG value

Add this temporarily to see the value:

```kotlin
@Composable
fun AppWithShowcase() {
    // Debug log
    println("IS_DEBUG value: ${com.hp.innovrex.showcase.IS_DEBUG}")
    
    // ...rest of code
}
```

### Check 5: Use the simplest possible FAB

Replace DebugShowcaseLauncher with this simple test:

```kotlin
Box(Modifier.fillMaxSize()) {
    MainAppContent()
    
    // Simple test FAB
    FloatingActionButton(
        onClick = { println("FAB clicked!") },
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        Text("TEST")
    }
}
```

If you see this, the issue is with DebugShowcaseLauncher logic.

---

## Alternative: Use Manual Button (100% Guaranteed to Work)

Replace your `AppWithShowcase()` with this super-simple version:

```kotlin
@Composable
fun AppWithShowcase() {
    var showShowcase by remember { mutableStateOf(false) }
    
    if (showShowcase) {
        Surface(Modifier.fillMaxSize()) {
            DesignSystemShowcase(onClose = { showShowcase = false })
        }
    } else {
        InnovrexTheme {
            Column(
                Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Innovrex App", style = MaterialTheme.typography.headlineLarge)
                Spacer(Modifier.height(32.dp))
                Button(onClick = { showShowcase = true }) {
                    Text("Open Design System Showcase")
                }
            }
        }
    }
}
```

This removes all complexity and just puts a simple button in the middle of the screen.

---

## Current File State

**AppWithShowcase.kt** now has:
- ✅ Manual showcase state management
- ✅ Fullscreen showcase when showShowcase = true
- ✅ DebugShowcaseLauncher FAB
- ✅ TEMPORARY test FAB (pink, offset 80dp from bottom-right)
- ✅ Clear instructions in main content

**BuildConfig.kt** now has:
- ✅ IS_DEBUG hardcoded to `true`
- ✅ No dependency on gradle build config (for now)

---

## Next Steps

Once you confirm the buttons are working:

1. **Remove the TEMPORARY FAB** - Delete this block from AppWithShowcase.kt:
   ```kotlin
   // TEMPORARY: Add a visible button for testing
   FloatingActionButton(
       onClick = { showShowcase = true },
       // ...
   ) {
       Text("🎨", style = MaterialTheme.typography.headlineMedium)
   }
   ```

2. **Configure proper IS_DEBUG** - Wire it to actual build configuration:
   ```kotlin
   // In BuildConfig.kt
   internal val IS_DEBUG: Boolean = BuildConfig.DEBUG
   ```

3. **Test release builds** - Verify FAB disappears in release

---

## Contact Points

If you still don't see buttons after these steps, check:
1. ✅ `AppWithShowcase()` is being called from your main `App.kt`
2. ✅ No errors in console
3. ✅ Project is rebuilt after changes
4. ✅ Using the correct composable (not an old cached version)

---

**Status**: You should now see at least the TEMPORARY pink FAB! 🎨

