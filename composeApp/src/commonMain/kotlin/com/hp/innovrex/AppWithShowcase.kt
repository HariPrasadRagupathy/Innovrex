package com.hp.innovrex

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.theme.InnovrexTheme
import com.hp.innovrex.showcase.DebugShowcaseLauncher
import com.hp.innovrex.showcase.IS_DEBUG

/**
 * Example integration of the Design System Showcase.
 * This shows how to integrate the showcase into your app.
 *
 * Recommended approach: Use DebugShowcaseLauncher (non-intrusive FAB)
 */

@Composable
fun AppWithShowcase() {
    InnovrexTheme {
        Box(Modifier.fillMaxSize()) {
            MainAppContent()

            if (IS_DEBUG) {
                // Single debug launcher FAB (non-intrusive)
                DebugShowcaseLauncher()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainAppContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Innovrex") })
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
            Text("Welcome to Innovrex", style = MaterialTheme.typography.headlineLarge)

            Spacer(Modifier.height(16.dp))

            Text(
                "Your main app content goes here",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (IS_DEBUG) {
                Spacer(Modifier.height(32.dp))

                Text(
                    "Design System Showcase (debug only): tap the 🎨 floating button in the bottom-right corner.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// For Desktop/JVM: Separate window example
// See ShowcaseWindow.kt in jvmMain for usage
