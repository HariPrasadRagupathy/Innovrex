package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Gesture thresholds and interaction tokens for swipe, drag, fling.
 * Platform-agnostic thresholds tuned for responsive UX.
 */
class GestureTokens(
    // Swipe thresholds
    val swipeVelocityThreshold: Dp = 125.dp, // minimum velocity (dp/s) to trigger swipe
    val swipeDistanceThreshold: Dp = 48.dp, // minimum distance to commit swipe
    val swipeFractionThreshold: Float = 0.25f, // fraction of screen width for swipe-to-dismiss

    // Drag thresholds
    val dragSlop: Dp = 8.dp, // movement before drag starts (touch slop)
    val longPressTimeout: Long = 500L, // ms before triggering long press

    // Fling decay rate
    val flingDecayFactor: Float = 0.99f, // multiplier per frame for deceleration

    // Pull-to-refresh thresholds
    val refreshTriggerDistance: Dp = 80.dp, // pull distance to trigger refresh
    val refreshIndicatorOffset: Dp = 64.dp, // offset of refresh indicator

    // Scroll velocity
    val fastScrollVelocity: Dp = 1000.dp, // dp/s considered "fast" for animations

    // Dismissal thresholds (e.g., swipe-to-delete card)
    val dismissDistanceThreshold: Dp = 100.dp,
    val dismissVelocityThreshold: Dp = 400.dp,
)

val LocalGestures = staticCompositionLocalOf { GestureTokens() }

