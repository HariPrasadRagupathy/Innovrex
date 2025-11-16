package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Screen transition tokens for navigation, modals, sheets, and page changes.
 * Supports multiplatform navigation patterns.
 */
class TransitionTokens(
    val navigationDuration: Int = 300,
    val modalDuration: Int = 250,
    val sheetDuration: Int = 350,
    val dialogDuration: Int = 200,
    val navigationEasing: Easing = FastOutSlowInEasing,
    val exitEasing: Easing = LinearOutSlowInEasing,
    val enterEasing: Easing = EaseOut,
    val sharedAxisEasing: Easing = EaseInOut,
    val slideOffsetFraction: Float = 0.15f,
    val fullSlideOffsetFraction: Float = 1.0f,
) {
    fun enterSlideLeft(): EnterTransition = slideInHorizontally(
        animationSpec = tween(navigationDuration, easing = navigationEasing),
        initialOffsetX = { fullWidth -> (fullWidth * fullSlideOffsetFraction).toInt() }
    ) + fadeIn(animationSpec = tween(navigationDuration))

    fun enterSlideRight(): EnterTransition = slideInHorizontally(
        animationSpec = tween(navigationDuration, easing = navigationEasing),
        initialOffsetX = { fullWidth -> -(fullWidth * fullSlideOffsetFraction).toInt() }
    ) + fadeIn(animationSpec = tween(navigationDuration))

    fun enterSlideUp(): EnterTransition = slideInVertically(
        animationSpec = tween(sheetDuration, easing = enterEasing),
        initialOffsetY = { fullHeight -> fullHeight }
    ) + fadeIn(animationSpec = tween(sheetDuration))

    fun enterSlideDown(): EnterTransition = slideInVertically(
        animationSpec = tween(modalDuration, easing = enterEasing),
        initialOffsetY = { fullHeight -> -fullHeight }
    ) + fadeIn(animationSpec = tween(modalDuration))

    fun enterSharedAxisForward(): EnterTransition = scaleIn(
        initialScale = 0.92f,
        animationSpec = tween(navigationDuration, easing = sharedAxisEasing)
    ) + fadeIn(animationSpec = tween(navigationDuration))

    fun enterSharedAxisBackward(): EnterTransition = scaleIn(
        initialScale = 1.08f,
        animationSpec = tween(navigationDuration, easing = sharedAxisEasing)
    ) + fadeIn(animationSpec = tween(navigationDuration))

    fun enterFade(): EnterTransition = fadeIn(
        animationSpec = tween(dialogDuration, easing = enterEasing)
    )

    fun exitSlideLeft(): ExitTransition = slideOutHorizontally(
        animationSpec = tween(navigationDuration, easing = exitEasing),
        targetOffsetX = { fullWidth -> -(fullWidth * slideOffsetFraction).toInt() }
    ) + fadeOut(animationSpec = tween(navigationDuration))

    fun exitSlideRight(): ExitTransition = slideOutHorizontally(
        animationSpec = tween(navigationDuration, easing = exitEasing),
        targetOffsetX = { fullWidth -> fullWidth }
    ) + fadeOut(animationSpec = tween(navigationDuration))

    fun exitSlideDown(): ExitTransition = slideOutVertically(
        animationSpec = tween(sheetDuration, easing = exitEasing),
        targetOffsetY = { fullHeight -> fullHeight }
    ) + fadeOut(animationSpec = tween(sheetDuration))

    fun exitSlideUp(): ExitTransition = slideOutVertically(
        animationSpec = tween(modalDuration, easing = exitEasing),
        targetOffsetY = { fullHeight -> -fullHeight }
    ) + fadeOut(animationSpec = tween(modalDuration))

    fun exitSharedAxisForward(): ExitTransition = scaleOut(
        targetScale = 0.92f,
        animationSpec = tween(navigationDuration, easing = sharedAxisEasing)
    ) + fadeOut(animationSpec = tween(navigationDuration))

    fun exitSharedAxisBackward(): ExitTransition = scaleOut(
        targetScale = 1.08f,
        animationSpec = tween(navigationDuration, easing = sharedAxisEasing)
    ) + fadeOut(animationSpec = tween(navigationDuration))

    fun exitFade(): ExitTransition = fadeOut(
        animationSpec = tween(dialogDuration, easing = exitEasing)
    )

    data class NavigationPattern(val enter: EnterTransition, val exit: ExitTransition)

    fun forwardNavigation() = NavigationPattern(
        enter = enterSlideLeft(),
        exit = exitSlideLeft()
    )

    fun backNavigation() = NavigationPattern(
        enter = enterSlideRight(),
        exit = exitSlideRight()
    )

    fun modalPresentation() = NavigationPattern(
        enter = enterSlideUp(),
        exit = exitSlideDown()
    )

    fun sharedAxisNavigation() = NavigationPattern(
        enter = enterSharedAxisForward(),
        exit = exitSharedAxisForward()
    )
}

val LocalTransitions = staticCompositionLocalOf { TransitionTokens() }

