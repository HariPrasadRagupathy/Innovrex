package com.hp.innovrex.showcase

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*

/**
 * Keyboard shortcut to toggle showcase.
 * Ctrl+Shift+K (or Cmd+Shift+K on Mac) to open showcase.
 *
 * Usage:
 * ```
 * Box(modifier = Modifier.debugShowcaseShortcut { showShowcase = true }) {
 *     AppContent()
 * }
 * ```
 */
@OptIn(ExperimentalComposeUiApi::class)
fun androidx.compose.ui.Modifier.debugShowcaseShortcut(
    onToggle: () -> Unit
): androidx.compose.ui.Modifier {
    if (!IS_DEBUG) return this

    return this.onPreviewKeyEvent { event ->
        if (event.type == KeyEventType.KeyDown) {
            val isCtrlOrCmd = event.isCtrlPressed || event.isMetaPressed
            val isShift = event.isShiftPressed
            val isK = event.key == Key.K

            if (isCtrlOrCmd && isShift && isK) {
                onToggle()
                true
            } else {
                false
            }
        } else {
            false
        }
    }
}

