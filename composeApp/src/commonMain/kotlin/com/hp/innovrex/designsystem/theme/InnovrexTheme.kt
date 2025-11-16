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

@Composable
fun InnovrexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = innovrexColorScheme(darkTheme),
    typography: Typography = TypographyTokens.MaterialTypography,
    shapes: Shapes = ShapeTokens.MaterialShapes,
    spacing: SpacingScale = SpacingScale(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalSpacing provides spacing) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

