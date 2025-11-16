package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Border widths & radii scale beyond general Shapes for granular control.
// These tokens let components request consistent stroke and corner values.
class BorderScale(
    val hairline: Dp = 0.5.dp, // ultra thin (may not render on all densities)
    val thin: Dp = 1.dp,
    val medium: Dp = 2.dp,
    val thick: Dp = 3.dp,
)

class RadiusScale(
    val none: Dp = 0.dp,
    val xs: Dp = 2.dp,
    val sm: Dp = 4.dp,
    val md: Dp = 8.dp,
    val lg: Dp = 12.dp,
    val xl: Dp = 16.dp,
    val pill: (height: Dp) -> Dp = { height -> height / 2 }, // dynamic radius for pill shapes
)

// CompositionLocals exposed via InnovrexTheme
val LocalBorderScale = staticCompositionLocalOf { BorderScale() }
val LocalRadiusScale = staticCompositionLocalOf { RadiusScale() }
