package com.hp.innovrex.showcase.tokens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalMotion
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val MotionTokensShowcase = ComponentItem(
    name = "Motion",
    description = "Durations, easing, and spring specs for animations.",
    category = ComponentCategory.TOKEN,
    preview = { MotionPreview() },
    controls = { MotionDescription() }
)

@Composable
private fun MotionPreview() {
    val motion = LocalMotion.current
    var toggled by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (toggled) 1.15f else 1f,
        animationSpec = motion.standardTweenMedium(), label = "scale"
    )
    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Box(Modifier.scale(scale)) {
            Button(onClick = { toggled = !toggled }) { Text("Animate") }
        }
        Text("Current tween: duration ${motion.durationMedium}ms easing standardEasing", style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun MotionDescription() {
    Text("Use MotionScale factory methods for consistent animation timings (short/medium/long).", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

