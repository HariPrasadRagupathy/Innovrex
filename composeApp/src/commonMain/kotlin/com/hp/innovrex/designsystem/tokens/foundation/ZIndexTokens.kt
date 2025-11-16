package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Z-index layering tokens for stacking order control.
 * Ensures modals, overlays, tooltips, and sheets appear in correct visual hierarchy.
 */
class ZIndexTokens(
    // Base layers
    val background: Float = 0f,
    val content: Float = 1f,
    val sticky: Float = 10f, // sticky headers, FABs

    // Navigation & chrome
    val appBar: Float = 100f,
    val navBar: Float = 100f,
    val tabBar: Float = 100f,

    // Overlays
    val scrim: Float = 200f, // darkening overlay behind modal
    val bottomSheet: Float = 300f,
    val sideSheet: Float = 300f,
    val dialog: Float = 400f,
    val modal: Float = 400f,

    // Notifications & feedback
    val snackbar: Float = 500f,
    val tooltip: Float = 600f,
    val popover: Float = 600f,

    // Top-most layers
    val dropdown: Float = 700f,
    val toast: Float = 800f, // system-level toast messages
    val debugOverlay: Float = 9999f, // dev tools overlay
)

val LocalZIndex = staticCompositionLocalOf { ZIndexTokens() }

