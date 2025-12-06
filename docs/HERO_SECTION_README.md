# Responsive Hero Section Component

## Overview

The Hero Section is a responsive, full-width landing page component designed for the Innovrex Kotlin Multiplatform website. It adapts seamlessly across mobile, tablet, and desktop breakpoints.

## Features

### 🎨 Design System Integration
- Uses the **red-based brand color palette** from the design system
- Follows Material 3 design principles
- Implements custom typography tokens
- Respects spacing and layout tokens

### 📱 Responsive Behavior
The component automatically adapts based on screen width:

| Breakpoint | Width | Layout Changes |
|------------|-------|----------------|
| **Mobile** | < 768dp | Vertical button stack, smaller text, compact padding |
| **Tablet** | 768dp - 1024dp | Moderate spacing, medium text sizes |
| **Desktop** | 1024dp - 1440dp | Horizontal buttons, large headlines, generous padding |
| **Wide** | 1440dp - 1920dp | Maximum content width, extra padding |
| **Ultra Wide** | > 1920dp | Optimized for large displays |

### 🎯 Key Components

#### 1. **Main Headline**
- Responsive font sizes: 32sp (mobile) to 72sp (ultra-wide)
- Bold weight, white color
- Center-aligned with max width constraints

#### 2. **Subtitle**
- Descriptive text explaining the value proposition
- Gray color (#E0E0E0) for visual hierarchy
- Responsive sizing: 16sp to 22sp

#### 3. **CTA Buttons**
- **Primary**: "Explore Our Solutions" (red background)
- **Secondary**: "Contact Us" (outlined)
- Layout switches from vertical (mobile) to horizontal (desktop)

#### 4. **Background Pattern**
- Dark gradient background (#0A0A0F to #1A1A24)
- Subtle red radial gradients for visual interest
- Simulates network/connection pattern

## Usage

### Basic Implementation

```kotlin
import com.hp.innovrex.features.home.ui.HomeScreen

@Composable
fun App() {
    InnovrexTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            onExploreClick = { /* Navigate to solutions */ },
            onContactClick = { /* Navigate to contact */ }
        )
    }
}
```

### Direct Hero Section Usage

```kotlin
import com.hp.innovrex.features.home.ui.components.HeroSection
import com.hp.innovrex.designsystem.utils.ScreenSize

@Composable
fun CustomLayout() {
    BoxWithConstraints {
        val screenWidth = with(LocalDensity.current) { maxWidth }
        val screenSize = rememberScreenSize(screenWidth)
        
        HeroSection(
            screenSize = screenSize,
            onExploreClick = { /* Action */ },
            onContactClick = { /* Action */ }
        )
    }
}
```

## File Structure

```
composeApp/src/commonMain/kotlin/com/hp/innovrex/
├── designsystem/
│   ├── components/
│   │   └── Button.kt              # Reusable button components
│   ├── theme/
│   │   └── Theme.kt               # Material 3 theme setup
│   ├── tokens/
│   │   └── foundation/
│   │       ├── ColorTokens.kt     # Brand colors & semantic tokens
│   │       ├── SpacingTokens.kt   # 8px grid spacing system
│   │       ├── TypographyTokens.kt # Text styles
│   │       └── BreakpointTokens.kt # Responsive breakpoints
│   └── utils/
│       └── ResponsiveUtils.kt     # Helper functions for responsive layouts
└── features/
    └── home/
        └── ui/
            ├── HomeScreen.kt                    # Main screen wrapper
            └── components/
                └── HeroSection.kt               # Hero section component
```

## Design Tokens Used

### Colors
- **Primary**: Red600 (#E53935) / Red400 (dark mode)
- **Background**: Dark (#0A0A0F)
- **Surface**: Dark Surface (#1A1A24)
- **Text**: White (#FFFFFF) / Gray300 (#E0E0E0)

### Spacing
- **Padding**: 24dp (mobile) → 128dp (desktop)
- **Vertical**: 48dp (mobile) → 160dp (wide)
- **Button gap**: 16dp

### Typography
- **Display Large**: 32sp - 72sp (responsive)
- **Title Large**: 16sp - 22sp (responsive)
- **Label Large**: 14sp (buttons)

## Customization

### Changing Colors
Edit `/designsystem/tokens/foundation/ColorTokens.kt`:
```kotlin
object BrandColors {
    val Red600 = Color(0xFFE53935) // Change your primary color here
}
```

### Adjusting Breakpoints
Edit `/designsystem/tokens/foundation/BreakpointTokens.kt`:
```kotlin
object BreakpointTokens {
    val Desktop: Dp = 1024.dp // Adjust breakpoint
}
```

### Custom Content
Replace text in `HeroSection.kt`:
```kotlin
Text(
    text = "Your Custom Headline", // Change here
    // ...
)
```

## Best Practices

1. **Always use BoxWithConstraints** for responsive layouts on web
2. **Test across breakpoints** using browser developer tools
3. **Use semantic colors** instead of hardcoded values
4. **Follow the 8px grid** for consistent spacing
5. **Maintain touch targets** minimum 48dp on mobile

## Browser Testing

Test the responsive layout at these widths:
- **Mobile**: 375px, 414px (iPhone)
- **Tablet**: 768px, 1024px (iPad)
- **Desktop**: 1280px, 1440px, 1920px

## Performance Notes

- Uses `remember` for computed values to avoid recomposition overhead
- Background gradients are lightweight and GPU-accelerated
- No heavy animations in initial render
- Optimized for Kotlin/Wasm compilation

## Accessibility

- ✅ High contrast text (white on dark)
- ✅ Readable font sizes (minimum 16sp on mobile)
- ✅ Touch-friendly buttons (48dp height)
- ✅ Semantic HTML structure (when compiled to web)
- ⚠️ TODO: Add screen reader labels
- ⚠️ TODO: Keyboard navigation support

## Future Enhancements

- [ ] Add animated background particles
- [ ] Implement parallax scrolling effect
- [ ] Add fade-in animations on load
- [ ] Support for custom fonts
- [ ] A/B testing variants
- [ ] Analytics integration for button clicks

## License

Part of the Innovrex Kotlin Multiplatform project.

