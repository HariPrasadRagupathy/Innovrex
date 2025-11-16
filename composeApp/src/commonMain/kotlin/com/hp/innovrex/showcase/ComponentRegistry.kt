package com.hp.innovrex.showcase

import androidx.compose.runtime.Composable
import com.hp.innovrex.showcase.components.RIButtonShowcase
import com.hp.innovrex.showcase.tokens.ColorTokensShowcase
import com.hp.innovrex.showcase.tokens.SpacingTokensShowcase

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
        // Add more token showcases (Typography, Shapes, Motion, etc.)
    )
}

