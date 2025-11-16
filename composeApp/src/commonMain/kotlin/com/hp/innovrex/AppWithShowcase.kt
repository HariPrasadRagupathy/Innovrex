package com.hp.innovrex

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.theme.InnovrexTheme
import com.hp.innovrex.showcase.DebugShowcaseLauncher

/**
 * Example integration of the Design System Showcase.
 * This shows how to integrate the showcase into your app.
 *
 * Recommended approach: Use DebugShowcaseLauncher (non-intrusive FAB)
 */

@Composable
fun AppWithShowcase() {
    var showShowcase by remember { mutableStateOf(false) }

    InnovrexTheme {
        if (showShowcase) {
            // Fullscreen showcase
            Surface(modifier = Modifier.fillMaxSize()) {
                com.hp.innovrex.showcase.DesignSystemShowcase(
                    onClose = { showShowcase = false }
                )
            }
        } else {
            Box(Modifier.fillMaxSize()) {
                // Your main app content
                MainAppContent()

                // Debug-only floating action button (bottom-right)
                // Automatically hidden in release builds
                DebugShowcaseLauncher()

                // TEMPORARY: Add a visible button for testing
                FloatingActionButton(
                    onClick = { showShowcase = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(80.dp), // Offset from the other FAB
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text("🎨", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainAppContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Innovrex") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Welcome to Innovrex",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Your main app content goes here",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(32.dp))

            Text(
                "Design System Showcase:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "• Tap the 🎨 button in the bottom-right corner\n" +
                "• Or click the button below",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// For Desktop/JVM: Separate window example
// See ShowcaseWindow.kt in jvmMain for usage

