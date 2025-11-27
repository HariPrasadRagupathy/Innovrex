package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.ShapeTokens
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val ShapeTokensShowcase = ComponentItem(
    name = "Shapes",
    description = "Corner radius scale driving component surface styling.",
    category = ComponentCategory.TOKEN,
    preview = { ShapePreview() },
    controls = { ShapeDescription() }
)

@Composable
private fun ShapePreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        ShapeSample("Small", ShapeTokens.Small)
        ShapeSample("Medium", ShapeTokens.Medium)
        ShapeSample("Large", ShapeTokens.Large)
        ShapeSample("Circle", CircleShape)
    }
}

@Composable
private fun ShapeSample(label: String, shape: Shape) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .size(64.dp)
                .clip(shape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {}
        Spacer(Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
private fun ShapeDescription() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
        Text("Shapes define component surfaces and affordances.", style = MaterialTheme.typography.bodyMedium)
        Text(
            "Scale: Small=4dp, Medium=8dp, Large=16dp; Circle used for avatars/FABs.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
