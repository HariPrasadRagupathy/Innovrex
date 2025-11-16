package com.hp.innovrex

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.theme.InnovrexTheme
import com.hp.innovrex.showcase.DesignSystemShowcase

/**
 * Example integration of the Design System Showcase.
 * This shows how to integrate the showcase into your app.
 */

@Composable
fun AppWithShowcase() {
    var showShowcase by remember { mutableStateOf(false) }

    InnovrexTheme {
        if (showShowcase) {
            // Fullscreen showcase
            DesignSystemShowcase(
                onClose = { showShowcase = false }
            )
        } else {
            // Your main app content
            MainAppContent(
                onOpenShowcase = { showShowcase = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainAppContent(onOpenShowcase: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Innovrex") },
                actions = {
                    // Debug-only showcase button
                    if (com.hp.innovrex.showcase.IS_DEBUG) {
                        IconButton(onClick = onOpenShowcase) {
                            Text("🎨") // Or use an Icon
                        }
                    }
                }
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

            // Debug showcase button (visible in debug builds only)
            if (com.hp.innovrex.showcase.IS_DEBUG) {
                Spacer(Modifier.height(32.dp))

                Button(onClick = onOpenShowcase) {
                    Text("Open Design System Showcase")
                }

                Text(
                    "(Debug only - hidden in release)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

// For Desktop/JVM: Separate window example
// See ShowcaseWindow.kt in jvmMain for usage

