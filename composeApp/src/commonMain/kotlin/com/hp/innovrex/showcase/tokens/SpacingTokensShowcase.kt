package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalSpacing
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val SpacingTokensShowcase = ComponentItem(
    name = "Spacing",
    description = "Consistent spacing scale for layouts and component padding.",
    category = ComponentCategory.TOKEN,
    preview = { SpacingTokensPreview() },
    controls = { SpacingTokensDescription() }
)

@Composable
private fun SpacingTokensPreview() {
    val spacing = LocalSpacing.current
    val spacings = listOf(
        "XXS" to spacing.xxs,
        "XS" to spacing.xs,
        "SM" to spacing.sm,
        "MD" to spacing.md,
        "LG" to spacing.lg,
        "XL" to spacing.xl,
        "XXL" to spacing.xxl,
        "Gutter" to spacing.gutter,
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        item {
            Text(
                "Spacing Scale",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(spacings) { (name, size) ->
            SpacingItem(name, size)
        }
    }
}

@Composable
private fun SpacingItem(name: String, size: Dp) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, style = MaterialTheme.typography.bodyMedium)
        Text(
            "${size.value.toInt()}dp",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SpacingTokensDescription() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Spacing System", style = MaterialTheme.typography.titleSmall)
        Text(
            "The spacing scale follows a t-shirt sizing pattern.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

