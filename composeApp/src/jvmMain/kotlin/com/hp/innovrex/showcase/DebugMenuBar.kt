package com.hp.innovrex.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar

// Desktop-only debug menu builder.
// Call this inside the Window { } scope in your desktop main() function.
// Example:
// fun main() = application {
//     Window(onCloseRequest = ::exitApplication) {
//         var showShowcase by remember { mutableStateOf(false) }
//         DebugMenuBar { showShowcase = true }
//         AppWithShowcase()
//     }
// }
// Will show a top-level "Debug" menu with an item that opens the Design System Showcase (only in debug builds).
@Composable
fun FrameWindowScope.DebugMenuBar(onOpenShowcase: () -> Unit) {
    if (!IS_DEBUG) return
    this.MenuBar {
        this.Menu("Debug", mnemonic = 'D') {
            this.Item(
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

