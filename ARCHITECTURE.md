# Innovrex Design System Architecture

## 📐 Recommended Architecture: Atomic Design + Clean Architecture

### Directory Structure

```
com.hp.innovrex/
├── designsystem/                    # ✅ Design System (UI primitives, no business logic)
│   ├── components/                  # Reusable UI components
│   │   ├── atoms/                   # Smallest indivisible components
│   │   │   ├── RIButton.kt         # Button with variants/sizes/shapes
│   │   │   ├── RITextField.kt      # Text input (future)
│   │   │   ├── RIIcon.kt           # Icon wrapper (future)
│   │   │   ├── RIAvatar.kt         # User avatar (future)
│   │   │   ├── RIBadge.kt          # Notification badge (future)
│   │   │   ├── RIChip.kt           # Filter/tag chip (future)
│   │   │   └── Atoms.kt            # Barrel export
│   │   ├── molecules/               # Simple component combinations
│   │   │   ├── RISearchBar.kt      # TextField + Icon (future)
│   │   │   ├── RIInputField.kt     # TextField + Label + Error (future)
│   │   │   ├── RICard.kt           # Surface + Content layout (future)
│   │   │   ├── RIListItem.kt       # Leading + Content + Trailing (future)
│   │   │   └── Molecules.kt        # Barrel export
│   │   └── organisms/               # Complex component compositions
│   │       ├── RIAppBar.kt         # TopAppBar with actions (future)
│   │       ├── RINavigationDrawer.kt # Drawer with menu (future)
│   │       ├── RIBottomNavigation.kt # Nav bar with items (future)
│   │       ├── RIModalBottomSheet.kt # Sheet with content (future)
│   │       └── Organisms.kt        # Barrel export
│   ├── theme/                       # Theme configuration & providers
│   │   ├── InnovrexTheme.kt        ✅ Main theme composable
│   │   └── ThemeConfig.kt          # Platform-specific overrides (future)
│   └── tokens/                      # Design tokens (constants, no UI)
│       ├── foundation/              ✅ Primitive tokens
│       │   ├── ColorTokens.kt      ✅ Brand palette & schemes
│       │   ├── TypographyTokens.kt ✅ Font scales
│       │   ├── SpacingTokens.kt    ✅ Layout spacing
│       │   ├── ShapeTokens.kt      ✅ Corner radii
│       │   ├── BorderTokens.kt     ✅ Stroke widths & radii
│       │   ├── ElevationTokens.kt  ✅ Shadow depths
│       │   ├── MotionTokens.kt     ✅ Animation durations/easings
│       │   ├── StateLayerTokens.kt ✅ Interaction opacities
│       │   ├── TransitionTokens.kt ✅ Screen transitions
│       │   ├── GestureTokens.kt    ✅ Swipe/drag thresholds
│       │   ├── ZIndexTokens.kt     ✅ Layering hierarchy
│       │   ├── OpacityTokens.kt    ✅ Visibility scales
│       │   └── BreakpointTokens.kt ✅ Responsive breakpoints
│       └── ButtonTokens.kt          ✅ Button-specific tokens
├── features/                        # Feature modules (business logic + UI)
│   ├── home/                        # Home feature
│   │   ├── presentation/            # UI layer (MVI)
│   │   │   ├── HomeScreen.kt       # Composable screen
│   │   │   ├── HomeIntent.kt       # User actions
│   │   │   ├── HomeState.kt        # UI state
│   │   │   └── HomeViewModel.kt    # State management
│   │   ├── domain/                  # Business logic
│   │   │   ├── model/              # Domain models
│   │   │   ├── usecase/            # Use cases
│   │   │   └── repository/         # Repository interfaces
│   │   └── data/                    # Data layer
│   │       ├── repository/         # Repository implementations
│   │       ├── datasource/         # Local/Remote data sources
│   │       └── mapper/             # DTO ↔ Domain mappers
│   ├── auth/                        # Auth feature (future)
│   └── profile/                     # Profile feature (future)
├── core/                            # Shared utilities (future)
│   ├── navigation/                  # Navigation logic
│   ├── util/                        # Extensions, helpers
│   └── data/                        # Shared data models
└── App.kt                           # App entry point

shared/                              # Shared KMP module (future)
└── (business logic, networking, db)
```

---

## 🎯 Architecture Principles

### 1. **Design System = UI Primitives Only**
- **No business logic**: Components receive data via parameters
- **No navigation**: Features handle navigation
- **No API calls**: Features manage data
- **Composable**: Can extract to separate Gradle module later

### 2. **Atomic Design Hierarchy**

#### **Atoms** (Indivisible UI elements)
- Single-purpose components
- Minimal props, highly reusable
- Examples: Button, TextField, Icon, Avatar, Badge, Checkbox, Radio
- **Rule**: Cannot contain other custom components (only Compose foundation/material)

#### **Molecules** (Simple combinations of atoms)
- 2-5 atoms combined for specific purpose
- Examples: SearchBar (TextField + Icon), InputField (TextField + Label + Error), ListItem (Icon + Text + Chevron)
- **Rule**: No business logic, just layout + state management

#### **Organisms** (Complex, potentially standalone sections)
- Multiple molecules/atoms forming a distinct UI section
- Examples: AppBar, NavigationDrawer, BottomNavigation, Header with logo/nav/actions
- **Rule**: Can manage local UI state (expanded/collapsed) but not domain data

### 3. **Token-Driven Design**
- All design decisions come from tokens
- Components use `LocalSpacing.current`, `LocalMotion.current`, etc.
- No magic numbers in component code
- Makes design changes global

### 4. **Feature Modules (MVI Clean Architecture)**
```kotlin
// Example: HomeScreen uses design system components
@Composable
fun HomeScreen(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit
) {
    val transitions = LocalTransitions.current
    
    Column {
        RIAppBar(title = "Home", onMenuClick = { onIntent(HomeIntent.OpenMenu) })
        
        LazyColumn {
            items(state.items) { item ->
                RICard(onClick = { onIntent(HomeIntent.SelectItem(item.id)) }) {
                    RIListItem(
                        leading = { RIAvatar(item.avatar) },
                        title = item.title,
                        subtitle = item.subtitle
                    )
                }
            }
        }
        
        RIButton(
            text = "Add Item",
            onClick = { onIntent(HomeIntent.AddItem) }
        )
    }
}
```

---

## 🔄 Migration Strategy

### Phase 1: ✅ Foundation (DONE)
- [x] Token system (colors, typography, spacing, motion, transitions, etc.)
- [x] Theme composable (InnovrexTheme)
- [x] Button component (RIButton with variants/shapes/sizes)

### Phase 2: Expand Atoms (NEXT)
- [ ] RITextField (text input with variants)
- [ ] RIIcon (icon wrapper with size/color)
- [ ] RIAvatar (user avatar with fallback)
- [ ] RIBadge (notification badge)
- [ ] RIChip (filter/tag chip)
- [ ] RICheckbox, RIRadio, RISwitch
- [ ] RIDivider, RISpacer

### Phase 3: Build Molecules
- [ ] RISearchBar (TextField + Icon)
- [ ] RIInputField (TextField + Label + Error + Helper)
- [ ] RICard (Surface + padding + elevation)
- [ ] RIListItem (Leading icon + content + trailing)
- [ ] RISegmentedButton (ButtonGroup)

### Phase 4: Build Organisms
- [ ] RIAppBar (TopAppBar with logo/title/actions)
- [ ] RINavigationDrawer (Drawer with menu items)
- [ ] RIBottomNavigation (Bottom nav with items)
- [ ] RIModalBottomSheet (Sheet with handle)
- [ ] RIDialog, RIAlertDialog

### Phase 5: Feature Implementation
- [ ] Create feature modules (home, auth, profile, etc.)
- [ ] Implement MVI architecture per feature
- [ ] Wire features to shared business logic
- [ ] Add navigation between features

---

## 📦 Import Patterns

### ✅ Recommended
```kotlin
// Import whole design system
import com.hp.innovrex.designsystem.components.atoms.*
import com.hp.innovrex.designsystem.theme.InnovrexTheme
import com.hp.innovrex.designsystem.tokens.foundation.*

// Use in feature
@Composable
fun MyFeatureScreen() {
    val spacing = LocalSpacing.current
    val transitions = LocalTransitions.current
    
    Column(Modifier.padding(spacing.lg)) {
        RIButton(text = "Click me", onClick = { /* ... */ })
    }
}
```

### ❌ Avoid
```kotlin
// Don't import individual token files in features
import com.hp.innovrex.designsystem.tokens.foundation.ColorTokens
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens

// Don't hardcode design values
Modifier.padding(16.dp) // ❌ Use LocalSpacing.current.lg
Color(0xFFD60000)        // ❌ Use MaterialTheme.colorScheme.primary or BrandPalette
```

---

## 🧪 Testing Strategy

### Unit Tests (Tokens)
```kotlin
class RIButtonTokensTest {
    @Test
    fun `Filled variant uses brand red`() {
        val colors = RIButtonDefaults.colors(RIButtonVariant.Filled, enabled = true)
        assertEquals(BrandPalette.PrimaryRed, colors.containerColor)
    }
}
```

### Screenshot Tests (Components)
```kotlin
@Test
fun `RIButton all variants snapshot`() = runComposeUiTest {
    setContent {
        InnovrexTheme {
            Column {
                RIButton(text = "Filled", variant = RIButtonVariant.Filled, onClick = {})
                RIButton(text = "Outlined", variant = RIButtonVariant.Outlined, onClick = {})
                // ... other variants
            }
        }
    }
    onRoot().captureToImage().assertAgainstGolden("button_variants")
}
```

### Integration Tests (Features)
```kotlin
@Test
fun `HomeScreen displays items correctly`() = runComposeUiTest {
    val state = HomeState(items = listOf(...))
    setContent {
        HomeScreen(state = state, onIntent = {})
    }
    onNodeWithText("Item 1").assertExists()
}
```

---

## 🚀 Benefits of This Architecture

✅ **Separation of Concerns**: Design system is pure UI, features handle business logic  
✅ **Reusability**: Components used across all features  
✅ **Testability**: Isolated components easy to test  
✅ **Scalability**: Add features without touching design system  
✅ **Maintainability**: Design changes in one place  
✅ **Collaboration**: Designers can work on design system, developers on features  
✅ **Extractable**: Can move design system to separate module/library later  
✅ **Platform-agnostic**: Same design system across Android/iOS/Web/Desktop  

---

## 🔧 Next Steps

1. **Create atom components** (TextField, Icon, Avatar, Badge, Chip, etc.)
2. **Build molecule components** (SearchBar, InputField, Card, ListItem)
3. **Add organism components** (AppBar, NavigationDrawer, BottomNavigation)
4. **Implement first feature** using MVI + design system components
5. **Add screenshot tests** for design system components
6. **Document component APIs** (props, variants, examples)
7. **Create design system showcase app** (catalog/storybook)

---

**Status**: ✅ Foundation complete, architecture defined, ready for component expansion

