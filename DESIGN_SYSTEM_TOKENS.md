# Innovrex Design System - Token Architecture

Complete foundation token layer for Kotlin Multiplatform Compose project.

## Foundation Tokens Created

### 1. **ColorTokens.kt**
- `BrandPalette`: Primary red colors (PrimaryRed, PrimaryRedDark, PrimaryRedLight, container variants)
- `innovrexColorScheme()`: Material3 ColorScheme builder for light/dark themes
- Red-based brand identity (#D60000 primary)

### 2. **TypographyTokens.kt**
- Complete Material3 typography scale (Display, Headline, Title, Body, Label)
- `MaterialTypography`: Ready-to-use Typography object
- Font sizes: 11sp–57sp with proper line heights

### 3. **ShapeTokens.kt**
- Small (4dp), Medium (8dp), Large (16dp) corner radii
- `MaterialShapes` for Material3 integration

### 4. **SpacingTokens.kt**
- Scale: xxs(2dp) → gutter(20dp)
- `LocalSpacing` CompositionLocal for consistent layout spacing

### 5. **BorderTokens.kt**
- `BorderScale`: hairline(0.5dp), thin(1dp), medium(2dp), thick(3dp)
- `RadiusScale`: none, xs, sm, md, lg, xl, pill(dynamic)
- Fine-grained control beyond ShapeTokens

### 6. **ElevationTokens.kt**
- 6 elevation levels (0dp–12dp)
- Semantic roles: navBar, card, sheet, dialog

### 7. **MotionTokens.kt**
- Durations: short(100ms), medium(200ms), long(400ms), extraLong(700ms)
- Easing curves: fadeIn, fadeOut, standard, linear, fast
- Spring specs: soft, medium, stiff
- Factory helpers: `standardTweenMedium()`, `fadeInSpec()`, etc.

### 8. **StateLayerTokens.kt**
- Interaction opacities: pressed(0.16), hovered(0.08), focused(0.12), dragged(0.12)
- `disabledContentAlpha` (0.38)

### 9. **TransitionTokens.kt** ✨ NEW
- **Navigation transitions**: enterSlideLeft/Right, exitSlideLeft/Right
- **Modal transitions**: enterSlideUp/Down, exitSlideUp/Down
- **Shared axis**: enterSharedAxisForward/Backward with scale + fade
- **Fade**: enterFade, exitFade for dialogs
- **Prebuilt patterns**: forwardNavigation(), backNavigation(), modalPresentation(), sharedAxisNavigation()
- Durations: navigation(300ms), modal(250ms), sheet(350ms), dialog(200ms)
- Platform-agnostic: works on Android Navigation, iOS swipes, web routing

### 10. **GestureTokens.kt** ✨ NEW
- **Swipe thresholds**: velocity(125dp), distance(48dp), fraction(0.25)
- **Drag**: slop(8dp), longPressTimeout(500ms)
- **Fling**: decayFactor(0.99)
- **Pull-to-refresh**: triggerDistance(80dp), indicatorOffset(64dp)
- **Dismissal**: distanceThreshold(100dp), velocityThreshold(400dp)

### 11. **ZIndexTokens.kt** ✨ NEW
- **Layering hierarchy**:
  - Base: background(0), content(1), sticky(10)
  - Chrome: appBar/navBar/tabBar(100)
  - Overlays: scrim(200), bottomSheet/sideSheet(300), dialog/modal(400)
  - Feedback: snackbar(500), tooltip/popover(600), dropdown(700), toast(800)
  - Debug: debugOverlay(9999)

### 12. **OpacityTokens.kt** ✨ NEW
- **Content visibility**: invisible(0), faint(0.12), subtle(0.38), medium(0.60), high(0.87), full(1)
- **State overlays**: statePressed(0.16), stateHovered(0.08), stateFocused(0.12), stateDragged(0.12)
- **Disabled states**: disabledContent(0.38), disabledContainer(0.12)
- **Scrim overlays**: scrimLight(0.32), scrimMedium(0.54), scrimHeavy(0.72)

## Theme Integration

### **InnovrexTheme.kt**
Centralized theme composable providing all tokens via CompositionLocals:
```kotlin
@Composable
fun InnovrexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = innovrexColorScheme(darkTheme),
    typography: Typography = TypographyTokens.MaterialTypography,
    shapes: Shapes = ShapeTokens.MaterialShapes,
    spacing: SpacingScale = SpacingScale(),
    borders: BorderScale = BorderScale(),
    radii: RadiusScale = RadiusScale(),
    elevation: ElevationScale = ElevationScale(),
    motion: MotionScale = MotionScale(),
    stateLayers: StateLayerOpacities = StateLayerOpacities(),
    breakpoints: Breakpoints = Breakpoints(),
    transitions: TransitionTokens = TransitionTokens(), // ✨
    gestures: GestureTokens = GestureTokens(),         // ✨
    zIndex: ZIndexTokens = ZIndexTokens(),             // ✨
    opacity: OpacityTokens = OpacityTokens(),          // ✨
    content: @Composable () -> Unit
)
```

## Component Tokens

### **ButtonTokens.kt**
- Variants: Filled, Tonal, Outlined, Ghost, Link, Danger
- Shapes: Rectangle, Rounded, Pill, Circle
- Sizes: Small, Medium, Large
- Uses `BrandPalette` for red-based button styling
- `RIButtonDefaults.colors()` & `RIButtonDefaults.metrics()`

## Usage Examples

### Screen Transitions (Navigation)
```kotlin
val transitions = LocalTransitions.current

AnimatedContent(
    targetState = currentScreen,
    transitionSpec = {
        when (targetState) {
            is Screen.Detail -> transitions.enterSlideLeft() togetherWith transitions.exitSlideLeft()
            is Screen.Back -> transitions.enterSlideRight() togetherWith transitions.exitSlideRight()
            is Screen.Modal -> transitions.modalPresentation().let { it.enter togetherWith it.exit }
        }
    }
) { screen -> /* content */ }
```

### Using Gesture Thresholds
```kotlin
val gestures = LocalGestures.current
val swipeState = rememberSwipeableState(...)

SwipeableCard(
    modifier = Modifier.swipeable(
        state = swipeState,
        anchors = mapOf(...),
        thresholds = { _, _ -> FractionalThreshold(gestures.swipeFractionThreshold) }
    )
)
```

### Z-Index Layering
```kotlin
val zIndex = LocalZIndex.current

Box(Modifier.zIndex(zIndex.dialog)) {
    Dialog(...)
}

Box(Modifier.zIndex(zIndex.scrim)) {
    Scrim(...)
}
```

### Opacity States
```kotlin
val opacity = LocalOpacity.current

Box(
    Modifier
        .alpha(if (isHovered) opacity.stateHovered else 0f)
        .background(MaterialTheme.colorScheme.primary.copy(alpha = opacity.stateHovered))
)
```

### Motion & Transitions Combined
```kotlin
val motion = LocalMotion.current
val transitions = LocalTransitions.current

LaunchedEffect(visible) {
    animate(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = motion.fadeInSpec()
    ) { value, _ -> alpha = value }
}
```

## File Structure
```
composeApp/src/commonMain/kotlin/com/hp/innovrex/
├── components/
│   └── atoms/
│       └── RIButton.kt
├── designsystem/
│   ├── theme/
│   │   └── InnovrexTheme.kt
│   └── tokens/
│       ├── ButtonTokens.kt
│       └── foundation/
│           ├── BorderTokens.kt
│           ├── BreakpointTokens.kt
│           ├── ColorTokens.kt
│           ├── ElevationTokens.kt
│           ├── GestureTokens.kt      ✨
│           ├── MotionTokens.kt
│           ├── OpacityTokens.kt      ✨
│           ├── ShapeTokens.kt
│           ├── SpacingTokens.kt
│           ├── StateLayerTokens.kt
│           ├── TransitionTokens.kt   ✨
│           ├── TypographyTokens.kt
│           └── ZIndexTokens.kt       ✨
```

## Next Steps

1. **Usage Integration**: Wire `InnovrexTheme` into your app entry point (e.g., `App.kt`)
2. **Navigation Setup**: Use `TransitionTokens` in your navigation library (Compose Navigation, Voyager, etc.)
3. **Custom Font**: Replace `FontFamily.Default` in `TypographyTokens` with brand font
4. **Dark Theme**: Extend `innovrexColorScheme()` with full dark palette
5. **Semantic Colors**: Add Success/Warning/Info color roles to `BrandPalette`
6. **Testing**: Write unit tests for token mapping (e.g., `RIButtonDefaults.colors()`)
7. **Documentation**: Create Storybook/catalog screens showing all button variants, transitions, etc.

## Benefits

✅ **Centralized tokens**: Single source of truth for design values  
✅ **Type-safe**: Sealed interfaces prevent invalid combinations  
✅ **Platform-agnostic**: Works across Android, iOS, Web, Desktop  
✅ **Themeable**: All tokens overridable via `InnovrexTheme` params  
✅ **Testable**: Pure mapping functions in Defaults objects  
✅ **Scalable**: Easy to add new tokens or component-specific values  
✅ **Accessible**: State layers, opacity, and semantics baked in  
✅ **Performant**: CompositionLocals avoid prop drilling  

---

**Status**: ✅ All tokens implemented, compiled, and ready for use  
**Warnings**: Only unused property warnings (expected until components consume tokens)

