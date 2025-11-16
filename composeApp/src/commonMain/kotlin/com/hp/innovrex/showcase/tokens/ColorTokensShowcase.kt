package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.BrandPalette
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val ColorTokensShowcase = ComponentItem(
    name = "Colors",
    description = "Brand color palette and semantic color roles.",
    category = ComponentCategory.TOKEN,
    preview = { ColorTokensPreview() },
    controls = { ColorTokensDescription() }
)

@Composable
private fun ColorTokensPreview() {
    val colors = listOf(
        "Primary Red" to BrandPalette.PrimaryRed,
        "Primary Red Dark" to BrandPalette.PrimaryRedDark,
        "Primary Red Light" to BrandPalette.PrimaryRedLight,
        "On Primary Red" to BrandPalette.OnPrimaryRed,
        "Primary Red Container" to BrandPalette.PrimaryRedContainer,
        "On Primary Red Container" to BrandPalette.OnPrimaryRedContainer,
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        item {
            Text(
                "Brand Palette",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(colors) { (name, color) ->
            ColorSwatch(name, color)
        }
    }
}

@Composable
private fun ColorSwatch(name: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color)
        )
        Column {
            Text(name, style = MaterialTheme.typography.bodyMedium)
            Text(
                colorToHex(color),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ColorTokensDescription() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Brand Colors", style = MaterialTheme.typography.titleSmall)
        Text(
            "The Innovrex brand uses a vibrant red as the primary color.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun colorToHex(color: Color): String {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    return buildString {
        append("#")
        append(red.toString(16).uppercase().padStart(2, '0'))
        append(green.toString(16).uppercase().padStart(2, '0'))
        append(blue.toString(16).uppercase().padStart(2, '0'))
    }
}


