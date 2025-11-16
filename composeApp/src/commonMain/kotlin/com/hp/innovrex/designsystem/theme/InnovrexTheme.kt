package com.hp.innovrex.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.ColorScheme
import com.hp.innovrex.designsystem.tokens.foundation.innovrexColorScheme
import com.hp.innovrex.designsystem.tokens.foundation.TypographyTokens
import com.hp.innovrex.designsystem.tokens.foundation.ShapeTokens
import com.hp.innovrex.designsystem.tokens.foundation.LocalSpacing
import com.hp.innovrex.designsystem.tokens.foundation.SpacingScale
import com.hp.innovrex.designsystem.tokens.foundation.LocalBorderScale
import com.hp.innovrex.designsystem.tokens.foundation.BorderScale
import com.hp.innovrex.designsystem.tokens.foundation.LocalRadiusScale
import com.hp.innovrex.designsystem.tokens.foundation.RadiusScale
import com.hp.innovrex.designsystem.tokens.foundation.LocalElevation
import com.hp.innovrex.designsystem.tokens.foundation.ElevationScale
import com.hp.innovrex.designsystem.tokens.foundation.LocalMotion
import com.hp.innovrex.designsystem.tokens.foundation.MotionScale
import com.hp.innovrex.designsystem.tokens.foundation.LocalStateLayers
import com.hp.innovrex.designsystem.tokens.foundation.StateLayerOpacities
import com.hp.innovrex.designsystem.tokens.foundation.LocalBreakpoints
import com.hp.innovrex.designsystem.tokens.foundation.Breakpoints

@Composable
fun InnovrexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = innovrexColorScheme(darkTheme),
    typography: Typography = TypographyTokens.MaterialTypography,
    shapes: Shapes = ShapeTokens.MaterialShapes,
    spacing: SpacingScale = SpacingScale(),
    borders: BorderScale = BorderScale(),
    radii: RadiusScale = RadiusScale(),
    elevation: ElevationScale = ElevationScale(),
    motion: MotionScale = MotionScale(),
    stateLayers: StateLayerOpacities = StateLayerOpacities(),
    breakpoints: Breakpoints = Breakpoints(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpacing provides spacing,
        LocalBorderScale provides borders,
        LocalRadiusScale provides radii,
        LocalElevation provides elevation,
        LocalMotion provides motion,
        LocalStateLayers provides stateLayers,
        LocalBreakpoints provides breakpoints,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
