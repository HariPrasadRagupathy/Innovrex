package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Tonal elevation levels (Material3-inspired) + semantic mapping.
class ElevationScale(
    val level0: Dp = 0.dp,
    val level1: Dp = 1.dp,
    val level2: Dp = 3.dp,
    val level3: Dp = 6.dp,
    val level4: Dp = 8.dp,
    val level5: Dp = 12.dp,
    // Semantic roles
    val navBar: Dp = level2,
    val card: Dp = level1,
    val sheet: Dp = level3,
    val dialog: Dp = level4,
)

val LocalElevation = staticCompositionLocalOf { ElevationScale() }

