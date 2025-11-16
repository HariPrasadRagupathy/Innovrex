package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Core brand palette for Innovrex (foundation-level tokens)
object BrandPalette {
    // Primary reds
    val PrimaryRed = Color(0xFFD60000)
    val PrimaryRedDark = Color(0xFF9E0000)
    val PrimaryRedLight = Color(0xFFFF5252)

    // Content (on-colors)
    val OnPrimaryRed = Color.White

    // Container / tonal variants
    val PrimaryRedContainer = Color(0xFFFFE5E5)
    val OnPrimaryRedContainer = Color(0xFF410000)

    // Additional semantic roles (can expand later)
    val ErrorRed = PrimaryRedDark
    val OnErrorRed = OnPrimaryRed
}

// Convenience builders for a Material3 ColorScheme using the brand palette.
// This can be extended with elevation overlays, outline, etc.
@Composable
fun innovrexColorScheme(darkTheme: Boolean = isSystemInDarkTheme()): ColorScheme {
    return if (darkTheme) {
        darkColorScheme(
            primary = BrandPalette.PrimaryRed,
            onPrimary = BrandPalette.OnPrimaryRed,
            primaryContainer = BrandPalette.PrimaryRedDark,
            onPrimaryContainer = BrandPalette.OnPrimaryRed,
            error = BrandPalette.ErrorRed,
            onError = BrandPalette.OnErrorRed,
        )
    } else {
        lightColorScheme(
            primary = BrandPalette.PrimaryRed,
            onPrimary = BrandPalette.OnPrimaryRed,
            primaryContainer = BrandPalette.PrimaryRedContainer,
            onPrimaryContainer = BrandPalette.OnPrimaryRedContainer,
            error = BrandPalette.ErrorRed,
            onError = BrandPalette.OnErrorRed,
        )
    }
}

