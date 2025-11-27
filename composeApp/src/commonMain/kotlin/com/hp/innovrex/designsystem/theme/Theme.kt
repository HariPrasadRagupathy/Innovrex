package com.hp.innovrex.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.hp.innovrex.designsystem.tokens.foundation.*

private val LightColorScheme = lightColorScheme(
    primary = LightColorTokens.Primary,
    onPrimary = LightColorTokens.OnPrimary,
    primaryContainer = LightColorTokens.PrimaryContainer,
    onPrimaryContainer = LightColorTokens.OnPrimaryContainer,
    secondary = LightColorTokens.Secondary,
    onSecondary = LightColorTokens.OnSecondary,
    secondaryContainer = LightColorTokens.SecondaryContainer,
    onSecondaryContainer = LightColorTokens.OnSecondaryContainer,
    background = LightColorTokens.Background,
    onBackground = LightColorTokens.OnBackground,
    surface = LightColorTokens.Surface,
    onSurface = LightColorTokens.OnSurface,
    surfaceVariant = LightColorTokens.SurfaceVariant,
    onSurfaceVariant = LightColorTokens.OnSurfaceVariant,
    error = LightColorTokens.Error,
    onError = LightColorTokens.OnError,
    outline = LightColorTokens.Outline,
    outlineVariant = LightColorTokens.OutlineVariant,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkColorTokens.Primary,
    onPrimary = DarkColorTokens.OnPrimary,
    primaryContainer = DarkColorTokens.PrimaryContainer,
    onPrimaryContainer = DarkColorTokens.OnPrimaryContainer,
    secondary = DarkColorTokens.Secondary,
    onSecondary = DarkColorTokens.OnSecondary,
    secondaryContainer = DarkColorTokens.SecondaryContainer,
    onSecondaryContainer = DarkColorTokens.OnSecondaryContainer,
    background = DarkColorTokens.Background,
    onBackground = DarkColorTokens.OnBackground,
    surface = DarkColorTokens.Surface,
    onSurface = DarkColorTokens.OnSurface,
    surfaceVariant = DarkColorTokens.SurfaceVariant,
    onSurfaceVariant = DarkColorTokens.OnSurfaceVariant,
    error = DarkColorTokens.Error,
    onError = DarkColorTokens.OnError,
    outline = DarkColorTokens.Outline,
    outlineVariant = DarkColorTokens.OutlineVariant,
)

@Composable
fun InnovrexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

