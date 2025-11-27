package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalGestures
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val GestureTokensShowcase = ComponentItem(
    name = "Gestures",
    description = "Interaction thresholds for swipe, drag, and refresh.",
    category = ComponentCategory.TOKEN,
    preview = { GesturePreview() },
    controls = { GestureDescription() }
)

@Composable
private fun GesturePreview() {
    val gestures = LocalGestures.current
    var show by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = { show = !show }) { Text("Show Thresholds") }
        if (show) {
            Text("Swipe distance: ${gestures.swipeDistanceThreshold}")
            Text("Drag slop: ${gestures.dragSlop}")
            Text("Refresh trigger: ${gestures.refreshTriggerDistance}")
            Text("Dismiss distance: ${gestures.dismissDistanceThreshold}")
        }
    }
}

@Composable
private fun GestureDescription() {
    Text("Gestures tokens centralize UX thresholds for consistent behavior.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

