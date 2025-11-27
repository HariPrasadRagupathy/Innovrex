package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalElevation
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val ElevationTokensShowcase = ComponentItem(
    name = "Elevation",
    description = "Tonal elevation scale for surfaces (cards, sheets, dialogs).",
    category = ComponentCategory.TOKEN,
    preview = { ElevationPreview() },
    controls = { ElevationDescription() }
)

@Composable
private fun ElevationPreview() {
    val elevation = LocalElevation.current
    Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        ElevationSample("L0", elevation.level0)
        ElevationSample("L1", elevation.level1)
        ElevationSample("L2", elevation.level2)
        ElevationSample("L3", elevation.level3)
        ElevationSample("L4", elevation.level4)
        ElevationSample("L5", elevation.level5)
    }
}

@Composable
private fun ElevationSample(label: String, dp: androidx.compose.ui.unit.Dp) {
    ElevatedCard(modifier = Modifier.size(72.dp)) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(label, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
private fun ElevationDescription() {
    Text("Higher levels indicate increased visual prominence; semantic roles mapped (card, sheet, dialog).", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

