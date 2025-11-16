package com.hp.showcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

/**
 * Public API entry point for embedding the design system showcase in any KMP project.
 * Host supplies a registry of showcase items (components, tokens, etc.).
 */

interface ShowcaseItem {
    val id: String
    val name: String
    val description: String
    @Composable fun Preview()
    @Composable fun Controls()
}

class ShowcaseRegistry(private val items: List<ShowcaseItem>) {
    val all: List<ShowcaseItem> get() = items
}

val LocalShowcaseRegistry = staticCompositionLocalOf<ShowcaseRegistry> { error("ShowcaseRegistry not provided") }

@Composable
fun ShowcaseHost(
    registry: ShowcaseRegistry,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    onRequestClose: (() -> Unit)? = null
) {
    androidx.compose.material3.MaterialTheme(colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()) {
        androidx.compose.runtime.CompositionLocalProvider(LocalShowcaseRegistry provides registry) {
            Surface(modifier = modifier) {
                ShowcaseScaffold(onRequestClose = onRequestClose)
            }
        }
    }
}

// Placeholder light/dark schemes (consumer overrides by wrapping ShowcaseHost in its own theme)
@Composable internal fun lightColorScheme() = MaterialTheme.colorScheme
@Composable internal fun darkColorScheme() = MaterialTheme.colorScheme

@Composable
private fun ShowcaseScaffold(onRequestClose: (() -> Unit)?) {
    val registry = LocalShowcaseRegistry.current
    val selected: MutableState<ShowcaseItem?> = remember { mutableStateOf(null) }
    androidx.compose.foundation.layout.Row(Modifier.fillMaxSize()) {
        androidx.compose.foundation.layout.Column(Modifier.width(240.dp).fillMaxHeight()) {
            androidx.compose.material3.Text("Showcase", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
            androidx.compose.foundation.lazy.LazyColumn {
                items(registry.all) { item ->
                    androidx.compose.material3.Text(
                        item.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selected.value = item }
                            .padding(12.dp)
                    )
                }
            }
        }
        androidx.compose.foundation.layout.Box(Modifier.weight(1f).fillMaxHeight()) {
            selected.value?.let { item ->
                androidx.compose.foundation.layout.Column(Modifier.fillMaxSize().padding(32.dp)) {
                    androidx.compose.material3.Text(item.name, style = MaterialTheme.typography.headlineMedium)
                    androidx.compose.material3.Text(item.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 24.dp))
                    item.Preview()
                    androidx.compose.material3.Divider(Modifier.padding(vertical = 24.dp))
                    item.Controls()
                }
            } ?: androidx.compose.material3.Text("Select an item", modifier = Modifier.align(Alignment.Center))
        }
    }
}

