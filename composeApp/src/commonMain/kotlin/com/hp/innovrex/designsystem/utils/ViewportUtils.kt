package com.hp.innovrex.designsystem.utils

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Web-specific utilities for detecting window/viewport size
 */

/**
 * Remember the current viewport width
 * This is a composable helper that works with BoxWithConstraints
 */
@Composable
fun rememberViewportWidth(maxWidth: Dp): Dp {
    return remember(maxWidth) { maxWidth }
}

/**
 * Check if the current screen is mobile-sized
 */
@Composable
fun isMobileScreen(screenSize: ScreenSize): Boolean {
    return screenSize == ScreenSize.Mobile
}

/**
 * Check if the current screen is tablet-sized or smaller
 */
@Composable
fun isTabletOrSmaller(screenSize: ScreenSize): Boolean {
    return screenSize <= ScreenSize.Tablet
}

/**
 * Check if the current screen is desktop-sized or larger
 */
@Composable
fun isDesktopOrLarger(screenSize: ScreenSize): Boolean {
    return screenSize >= ScreenSize.Desktop
}

/**
 * Helper to convert px to Dp
 */
@Composable
fun Int.pxToDp(): Dp {
    val density = LocalDensity.current
    return with(density) { this@pxToDp.toDp() }
}

/**
 * Helper to convert Dp to px
 */
@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}

