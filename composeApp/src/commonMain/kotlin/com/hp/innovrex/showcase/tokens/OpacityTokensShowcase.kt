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
import com.hp.innovrex.designsystem.tokens.foundation.LocalOpacity
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val OpacityTokensShowcase = ComponentItem(
    name = "Opacity",
    description = "Opacity scale for content visibility and scrims.",
    category = ComponentCategory.TOKEN,
    preview = { OpacityPreview() },
    controls = { OpacityDescription() }
)

@Composable
private fun OpacityPreview() {
    val opacity = LocalOpacity.current
    Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OpacitySample("Faint", opacity.faint)
        OpacitySample("Subtle", opacity.subtle)
        OpacitySample("Medium", opacity.medium)
        OpacitySample("High", opacity.high)
        OpacitySample("Scrim", opacity.scrimMedium)
    }
}

@Composable
private fun OpacitySample(label: String, alpha: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(48.dp).background(Color.Red.copy(alpha = alpha))) {}
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun OpacityDescription() {
    Text("Use semantic opacity tokens instead of hard-coded alpha values.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

