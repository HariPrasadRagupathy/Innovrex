package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation tokens for shadow/depth effects
 */
object ElevationTokens {
    val Level0: Dp = 0.dp    // No elevation
    val Level1: Dp = 1.dp    // Subtle elevation
    val Level2: Dp = 3.dp    // Cards
    val Level3: Dp = 6.dp    // Elevated cards
    val Level4: Dp = 8.dp    // Dialogs
    val Level5: Dp = 12.dp   // Modals

    // Semantic tokens
    val Card: Dp = Level2
    val Button: Dp = Level2
    val Modal: Dp = Level5
    val Tooltip: Dp = Level3
}

