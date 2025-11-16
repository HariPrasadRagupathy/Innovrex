package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf

// State layer opacities for interaction feedback (press/hover/focus/drag).
class StateLayerOpacities(
    val pressed: Float = 0.16f,
    val hovered: Float = 0.08f,
    val focused: Float = 0.12f,
    val dragged: Float = 0.12f,
    val disabledContentAlpha: Float = 0.38f,
)

val LocalStateLayers = staticCompositionLocalOf { StateLayerOpacities() }

