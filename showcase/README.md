# KMP Design System Showcase Library

Reusable Kotlin Multiplatform (Compose) module providing a plug-in showcase host for component and token previews.

## Module Goals
- Drop-in catalog for any KMP project (Android / Desktop / Web / iOS*)
- Extensible registry-based item system
- Decoupled from specific design tokens / themes
- Opt-in dark theme toggle by consumer

## Quick Start

```kotlin
val buttonItem = object : ShowcaseItem {
  override val id = "button"
  override val name = "Button"
  override val description = "Primary button variants"
  @Composable override fun Preview() { /* render button */ }
  @Composable override fun Controls() { /* knobs */ }
}

ShowcaseHost(registry = ShowcaseRegistry(listOf(buttonItem)))
```

Wrap in your app theme for colors/typography:
```kotlin
MyAppTheme { ShowcaseHost(registry) }
```

## Planned Enhancements
- Collapsible sidebar (port from app version)
- Built-in dark/light toggle
- Search & filtering
- Grouping & categories
- Screenshot export / code snippet copy

## License
Apache 2.0 (add full text if distributing externally)

