package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.runtime.staticCompositionLocalOf

// Motion / animation timing & easing tokens.
// Defines a consistent animation vocabulary across platforms.
class MotionScale(
    // Durations (ms)
    val durationShort: Int = 100,
    val durationMedium: Int = 200,
    val durationLong: Int = 400,
    val durationExtraLong: Int = 700,
    // Easing tokens
    val fadeInEasing: Easing = EaseIn,
    val fadeOutEasing: Easing = EaseOut,
    val standardEasing: Easing = EaseInOut,
    val linear: Easing = LinearEasing,
    // Additional interaction easings
    val fastEasing: Easing = EaseOut, // quick exit
    val enteringEasing: Easing = EaseInOut,
    // Spring specs (semantic)
    val springSoft: SpringSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    ),
    val springMedium: SpringSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMedium
    ),
    val springStiff: SpringSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessHigh
    ),
) {
    // Factory helpers for tween specs using standardized durations & easing
    fun standardTweenShort(): TweenSpec<Float> = tween(durationShort, easing = standardEasing)
    fun standardTweenMedium(): TweenSpec<Float> = tween(durationMedium, easing = standardEasing)
    fun standardTweenLong(): TweenSpec<Float> = tween(durationLong, easing = standardEasing)

    fun fadeInSpec(): TweenSpec<Float> = tween(durationMedium, easing = fadeInEasing)
    fun fadeOutSpec(): TweenSpec<Float> = tween(durationMedium, easing = fadeOutEasing)

    fun fastExitSpec(): TweenSpec<Float> = tween(durationShort, easing = fastEasing)
}

// CompositionLocal provided by theme
val LocalMotion = staticCompositionLocalOf { MotionScale() }

// Usage example (documentation):
// val motion = LocalMotion.current
// val anim = remember { Animatable(0f) }
// LaunchedEffect(Unit) { anim.animateTo(1f, animationSpec = motion.standardTweenMedium()) }
