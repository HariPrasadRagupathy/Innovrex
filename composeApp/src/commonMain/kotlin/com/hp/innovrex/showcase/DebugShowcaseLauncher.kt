package com.hp.innovrex.showcase

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Debug-only showcase launcher.
 * Adds a floating action button to your app that opens the showcase.
 * Only visible in debug builds - completely removed in release.
 *
 * Usage:
 * ```
 * @Composable
 * fun App() {
 *     Box {
 *         YourMainAppContent()
 *         DebugShowcaseLauncher() // Add this
 *     }
 * }
 * ```
 */
@Composable
fun DebugShowcaseLauncher(
    modifier: Modifier = Modifier
) {
    if (!IS_DEBUG) return // Completely removed in release builds

    var showShowcase by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        // Floating Action Button (bottom-right corner)
        AnimatedVisibility(
            visible = !showShowcase,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Expanded menu
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Quick action chips
                        Surface(
                            onClick = { showShowcase = true },
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                "Design System Showcase",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }

                // Main FAB
                FloatingActionButton(
                    onClick = {
                        if (isExpanded) {
                            showShowcase = true
                            isExpanded = false
                        } else {
                            isExpanded = !isExpanded
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ) {
                    Text(
                        "🎨",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }

        // Showcase fullscreen dialog
        if (showShowcase) {
            ShowcaseDialog(
                onDismiss = { showShowcase = false }
            )
        }
    }
}

@Composable
private fun ShowcaseDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            DesignSystemShowcase(onClose = onDismiss)
        }
    }
}

/**
 * Alternative: Simple FAB without expand menu.
 * Even less intrusive.
 */
@Composable
fun DebugShowcaseLauncherSimple(
    modifier: Modifier = Modifier
) {
    if (!IS_DEBUG) return

    var showShowcase by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        if (!showShowcase) {
            FloatingActionButton(
                onClick = { showShowcase = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f),
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Text("🎨", style = MaterialTheme.typography.titleMedium)
            }
        }

        if (showShowcase) {
            ShowcaseDialog(onDismiss = { showShowcase = false })
        }
    }
}

