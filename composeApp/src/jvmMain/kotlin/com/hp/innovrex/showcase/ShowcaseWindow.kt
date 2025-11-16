package com.hp.innovrex.showcase

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window

/**
 * Launch the design system showcase in a separate window (Desktop/JVM only).
 * This is useful for development to preview components alongside your app.
 */
@Composable
fun ShowcaseWindow(onCloseRequest: () -> Unit = {}) {
    if (IS_DEBUG) {
        Window(
            onCloseRequest = onCloseRequest,
            title = "Innovrex Design System Showcase"
        ) {
            DesignSystemShowcase(onClose = onCloseRequest)
        }
    }
}

