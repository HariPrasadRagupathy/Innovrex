package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalBorderScale
import com.hp.innovrex.designsystem.tokens.foundation.LocalRadiusScale
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val BorderTokensShowcase = ComponentItem(
    name = "Border & Radius",
    description = "Consistent stroke widths and corner radii beyond shape presets.",
    category = ComponentCategory.TOKEN,
    preview = { BorderPreview() },
    controls = { BorderDescription() }
)

@Composable
private fun BorderPreview() {
    val borders = LocalBorderScale.current
    val radii = LocalRadiusScale.current
    Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        BorderSample("Hairline", borders.hairline)
        BorderSample("Thin", borders.thin)
        BorderSample("Medium", borders.medium)
        BorderSample("Thick", borders.thick)
        RadiusSample("XS", radii.xs)
        RadiusSample("LG", radii.lg)
    }
}

@Composable
private fun BorderSample(label: String, width: androidx.compose.ui.unit.Dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier.size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(width, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
        ) {}
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun RadiusSample(label: String, radius: androidx.compose.ui.unit.Dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier.size(56.dp)
                .clip(RoundedCornerShape(radius))
                .border(1.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(radius))
        ) {}
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun BorderDescription() {
    Text("Border scale: 0.5dp, 1dp, 2dp, 3dp. Radius scale: 0/2/4/8/12/16dp + pill(height/2).", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

