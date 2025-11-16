package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf

// Responsive breakpoints (px values) for adaptive layouts (web / desktop).
class Breakpoints(
    val mobile: Int = 0,
    val mobileLarge: Int = 480,
    val tablet: Int = 768,
    val tabletLarge: Int = 992,
    val desktop: Int = 1200,
    val desktopLarge: Int = 1440,
)

val LocalBreakpoints = staticCompositionLocalOf { Breakpoints() }

