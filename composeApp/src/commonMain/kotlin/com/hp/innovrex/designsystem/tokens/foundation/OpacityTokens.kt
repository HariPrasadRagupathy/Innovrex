package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Opacity tokens for layered content, disabled states, and scrim overlays.
 */
class OpacityTokens(
    // Content visibility
    val invisible: Float = 0f,
    val faint: Float = 0.12f,
    val subtle: Float = 0.38f,
    val medium: Float = 0.60f,
    val high: Float = 0.87f,
    val full: Float = 1f,

    // State overlays (press, hover, focus)
    val statePressed: Float = 0.16f,
    val stateHovered: Float = 0.08f,
    val stateFocused: Float = 0.12f,
    val stateDragged: Float = 0.12f,

    // Disabled states
    val disabledContent: Float = 0.38f,
    val disabledContainer: Float = 0.12f,

    // Scrim (darkening overlay behind modal/dialog)
    val scrimLight: Float = 0.32f,
    val scrimMedium: Float = 0.54f,
    val scrimHeavy: Float = 0.72f,
)

val LocalOpacity = staticCompositionLocalOf { OpacityTokens() }

