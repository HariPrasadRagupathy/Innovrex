package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Border width tokens for consistent stroke widths
 */
object BorderTokens {
    val None: Dp = 0.dp
    val Thin: Dp = 1.dp
    val Medium: Dp = 2.dp
    val Thick: Dp = 4.dp
    val ExtraThick: Dp = 8.dp

    // Semantic tokens
    val Default: Dp = Thin
    val Focus: Dp = Medium
    val Error: Dp = Medium
}

