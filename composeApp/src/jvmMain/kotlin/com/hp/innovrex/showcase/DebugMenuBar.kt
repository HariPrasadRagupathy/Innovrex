package com.hp.innovrex.showcase

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar

// Desktop-only debug menu builder.
// Call this inside the desktop 'application { }' or alongside Window definitions, NOT inside a @Composable.
// Example:
// fun main() = application {
//     DebugMenuBar { showShowcase = true }
//     Window(onCloseRequest = ::exitApplication) { AppWithShowcase() }
// }
// Will show a top-level "Debug" menu with an item that opens the Design System Showcase (only in debug builds).
fun DebugMenuBar(onOpenShowcase: () -> Unit) {
    if (!IS_DEBUG) return
    MenuBar {
        Menu("Debug", mnemonic = 'D') {
            Item(
                text = "Design System Showcase",
                onClick = onOpenShowcase,
                shortcut = KeyShortcut(
                    key = Key.K,
                    ctrl = true,
                    shift = true // Ctrl+Shift+K / Cmd+Shift+K
                )
            )
        }
    }
}

// Optional: If you need an in-window composable fallback, you can add:
// @Composable
// fun DebugMenuBarInWindow(onOpenShowcase: () -> Unit) {
//     // Compose Desktop currently doesn't render MenuBar inside window content; prefer application scope.
// }
