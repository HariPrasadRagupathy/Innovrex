package com.hp.innovrex.showcase

import androidx.compose.runtime.Composable
import com.hp.innovrex.showcase.components.RIButtonShowcase
import com.hp.innovrex.showcase.tokens.ColorTokensShowcase
import com.hp.innovrex.showcase.tokens.SpacingTokensShowcase
import com.hp.innovrex.showcase.tokens.ShapeTokensShowcase
import com.hp.innovrex.showcase.tokens.BorderTokensShowcase
import com.hp.innovrex.showcase.tokens.TypographyTokensShowcase
import com.hp.innovrex.showcase.tokens.ElevationTokensShowcase
import com.hp.innovrex.showcase.tokens.MotionTokensShowcase
import com.hp.innovrex.showcase.tokens.StateLayerTokensShowcase
import com.hp.innovrex.showcase.tokens.OpacityTokensShowcase
import com.hp.innovrex.showcase.tokens.BreakpointTokensShowcase
import com.hp.innovrex.showcase.tokens.ZIndexTokensShowcase
import com.hp.innovrex.showcase.tokens.GestureTokensShowcase
import com.hp.innovrex.showcase.tokens.TransitionTokensShowcase

/**
 * Represents a component in the showcase catalog.
 */
data class ComponentItem(
    val name: String,
    val description: String,
    val category: ComponentCategory,
    val preview: @Composable () -> Unit,
    val controls: @Composable () -> Unit = {}
)

enum class ComponentCategory {
    ATOM,
    MOLECULE,
    ORGANISM,
    TOKEN
}

/**
 * Central registry of all components available in the showcase.
 * Add new components here to make them available in the showcase.
 */
object ComponentRegistry {
    val atoms: List<ComponentItem> = listOf(
        RIButtonShowcase,
        // Add more atoms here as you create them
    )

    val molecules: List<ComponentItem> = listOf(
        // Add molecules here (e.g., SearchBar, InputField, Card)
    )

    val organisms: List<ComponentItem> = listOf(
        // Add organisms here (e.g., AppBar, NavigationDrawer)
    )

    val tokens: List<ComponentItem> = listOf(
        ColorTokensShowcase,
        SpacingTokensShowcase,
        ShapeTokensShowcase,
        BorderTokensShowcase,
        TypographyTokensShowcase,
        ElevationTokensShowcase,
        MotionTokensShowcase,
        StateLayerTokensShowcase,
        OpacityTokensShowcase,
        BreakpointTokensShowcase,
        ZIndexTokensShowcase,
        GestureTokensShowcase,
        TransitionTokensShowcase,
        // Add more token showcases (Typography, Shapes, Motion, etc.)
    )

    private val tokensCheck = listOf(
        com.hp.innovrex.showcase.tokens.ShapeTokensShowcase.name
    )
}
