package com.hp.innovrex.showcase

import androidx.compose.runtime.Composable

/**
 * Debug-only design system showcase.
 * This module is only accessible in DEBUG builds.
 * Use this to preview and test design system components with interactive controls.
 */

/**
 * Showcase entry point - only renders in debug mode.
 * In release builds, this composable does nothing.
 */
@Composable
fun DesignSystemShowcase(onClose: () -> Unit = {}) {
    if (IS_DEBUG) {
        ShowcaseContent(onClose)
    }
}


