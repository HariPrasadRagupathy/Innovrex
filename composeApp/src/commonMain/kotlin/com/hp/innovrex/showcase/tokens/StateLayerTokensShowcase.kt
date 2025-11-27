package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalStateLayers
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val StateLayerTokensShowcase = ComponentItem(
    name = "State Layers",
    description = "Opacity overlays for press, hover, focus, drag interactions.",
    category = ComponentCategory.TOKEN,
    preview = { StateLayersPreview() },
    controls = { StateLayersDescription() }
)

@Composable
private fun StateLayersPreview() {
    val layers = LocalStateLayers.current
    Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        StateBox("Pressed", layers.pressed)
        StateBox("Hovered", layers.hovered)
        StateBox("Focused", layers.focused)
        StateBox("Dragged", layers.dragged)
    }
}

@Composable
private fun StateBox(label: String, alpha: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier.size(56.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = alpha))
        ) {}
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun StateLayersDescription() {
    Text("State layer opacities ensure consistent feedback across components.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

