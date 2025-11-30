package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.ui.graphics.Color

/**
 * Brand Color Palette
 * Primary brand colors based on red color scheme
 */
object BrandColors {
    // Red-based primary colors
    val Red50 = Color(0xFFFFEBEE)
    val Red100 = Color(0xFFFFCDD2)
    val Red200 = Color(0xFFEF9A9A)
    val Red300 = Color(0xFFE57373)
    val Red400 = Color(0xFFEF5350)
    val Red500 = Color(0xFFF44336) // Primary brand red
    val Red600 = Color(0xFFE53935)
    val Red700 = Color(0xFFD32F2F)
    val Red800 = Color(0xFFC62828)
    val Red900 = Color(0xFFB71C1C)

    // Dark red variants
    val DarkRed = Color(0xFF8B0000)
    val CrimsonRed = Color(0xFFDC143C)

    // Green colors for success states
    val Green50 = Color(0xFFE8F5E9)
    val Green100 = Color(0xFFC8E6C9)
    val Green500 = Color(0xFF4CAF50)
    val Green600 = Color(0xFF43A047)
    val Green700 = Color(0xFF388E3C)

    // Neutral colors
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Gray50 = Color(0xFFFAFAFA)
    val Gray100 = Color(0xFFF5F5F5)
    val Gray200 = Color(0xFFEEEEEE)
    val Gray300 = Color(0xFFE0E0E0)
    val Gray400 = Color(0xFFBDBDBD)
    val Gray500 = Color(0xFF9E9E9E)
    val Gray600 = Color(0xFF757575)
    val Gray700 = Color(0xFF616161)
    val Gray800 = Color(0xFF424242)
    val Gray900 = Color(0xFF212121)

    // Background colors
    val DarkBackground = Color(0xFF0A0A0F)
    val DarkSurface = Color(0xFF1A1A24)
    val DarkSurfaceVariant = Color(0xFF2A2A3A)
}

/**
 * Semantic color tokens for light theme
 */
object LightColorTokens {
    val Primary = BrandColors.Red600
    val OnPrimary = BrandColors.White
    val PrimaryContainer = BrandColors.Red100
    val OnPrimaryContainer = BrandColors.Red900

    val Secondary = BrandColors.Gray700
    val OnSecondary = BrandColors.White
    val SecondaryContainer = BrandColors.Gray200
    val OnSecondaryContainer = BrandColors.Gray900

    val Background = BrandColors.White
    val OnBackground = BrandColors.Gray900
    val Surface = BrandColors.White
    val OnSurface = BrandColors.Gray900
    val SurfaceVariant = BrandColors.Gray100
    val OnSurfaceVariant = BrandColors.Gray700

    val Error = BrandColors.Red700
    val OnError = BrandColors.White

    val Outline = BrandColors.Gray400
    val OutlineVariant = BrandColors.Gray200
}

/**
 * Semantic color tokens for dark theme
 */
object DarkColorTokens {
    val Primary = BrandColors.Red400
    val OnPrimary = BrandColors.White
    val PrimaryContainer = BrandColors.Red800
    val OnPrimaryContainer = BrandColors.Red100

    val Secondary = BrandColors.Gray400
    val OnSecondary = BrandColors.Gray900
    val SecondaryContainer = BrandColors.Gray700
    val OnSecondaryContainer = BrandColors.Gray100

    val Background = BrandColors.DarkBackground
    val OnBackground = BrandColors.Gray100
    val Surface = BrandColors.DarkSurface
    val OnSurface = BrandColors.Gray100
    val SurfaceVariant = BrandColors.DarkSurfaceVariant
    val OnSurfaceVariant = BrandColors.Gray400

    val Error = BrandColors.Red400
    val OnError = BrandColors.Gray900

    val Outline = BrandColors.Gray600
    val OutlineVariant = BrandColors.Gray700
}

