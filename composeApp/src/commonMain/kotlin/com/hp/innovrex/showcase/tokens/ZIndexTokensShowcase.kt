package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalZIndex
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val ZIndexTokensShowcase = ComponentItem(
    name = "Z-Index",
    description = "Layering order for overlays, navigation, and dialogs.",
    category = ComponentCategory.TOKEN,
    preview = { ZIndexPreview() },
    controls = { ZIndexDescription() }
)

@Composable
private fun ZIndexPreview() {
    val z = LocalZIndex.current
    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Content: ${z.content}")
        Text("AppBar: ${z.appBar}")
        Text("Sheet: ${z.bottomSheet}")
        Text("Dialog: ${z.dialog}")
        Text("Tooltip: ${z.tooltip}")
        Text("Debug Overlay: ${z.debugOverlay}")
    }
}

@Composable
private fun ZIndexDescription() {
    Text("Use semantic z-index tokens instead of arbitrary floats.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

