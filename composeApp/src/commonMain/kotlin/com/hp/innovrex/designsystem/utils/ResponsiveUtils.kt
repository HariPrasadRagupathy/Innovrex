package com.hp.innovrex.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.hp.innovrex.designsystem.tokens.foundation.BreakpointTokens

/**
 * Screen size categories
 */
enum class ScreenSize {
    Mobile,
    Tablet,
    Desktop,
    Wide,
    UltraWide
}

/**
 * Get current screen size based on width
 */
@Composable
fun rememberScreenSize(width: Dp): ScreenSize {
    return when {
        width >= BreakpointTokens.UltraWide -> ScreenSize.UltraWide
        width >= BreakpointTokens.Wide -> ScreenSize.Wide
        width >= BreakpointTokens.Desktop -> ScreenSize.Desktop
        width >= BreakpointTokens.Tablet -> ScreenSize.Tablet
        else -> ScreenSize.Mobile
    }
}

/**
 * Responsive value selector based on screen size
 */
@Composable
fun <T> responsiveValue(
    mobile: T,
    tablet: T = mobile,
    desktop: T = tablet,
    wide: T = desktop,
    ultraWide: T = wide,
    screenSize: ScreenSize
): T {
    return when (screenSize) {
        ScreenSize.Mobile -> mobile
        ScreenSize.Tablet -> tablet
        ScreenSize.Desktop -> desktop
        ScreenSize.Wide -> wide
        ScreenSize.UltraWide -> ultraWide
    }
}

