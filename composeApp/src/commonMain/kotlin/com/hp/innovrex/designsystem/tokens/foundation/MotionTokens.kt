package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Motion and animation duration tokens
 */
object MotionTokens {
    // Duration in milliseconds
    const val DurationInstant = 0
    const val DurationFast = 100
    const val DurationNormal = 200
    const val DurationSlow = 300
    const val DurationSlower = 500

    // Semantic durations
    const val Fade = DurationNormal
    const val Expand = DurationSlow
    const val Collapse = DurationSlow
    const val Slide = DurationNormal
    const val Scale = DurationFast
}

/**
 * Transition tokens for screen/page transitions
 */
object TransitionTokens {
    // Navigation patterns
    enum class NavigationPattern {
        SLIDE_LEFT,
        SLIDE_RIGHT,
        SLIDE_UP,
        SLIDE_DOWN,
        FADE,
        SCALE,
        NONE
    }

    const val DefaultDuration = 300
    const val SlowDuration = 500
    const val FastDuration = 200

    // Transition distances
    val SlideDistance: Dp = 24.dp
}

/**
 * Gesture tokens for interaction feedback
 */
object GestureTokens {
    const val TapScale = 0.95f
    const val PressAlpha = 0.12f
    const val HoverAlpha = 0.08f
    const val FocusAlpha = 0.12f
}

/**
 * State layer tokens for interactive states
 */
object StateLayerTokens {
    const val Hover = 0.08f
    const val Focus = 0.12f
    const val Press = 0.12f
    const val Drag = 0.16f
}

