package com.hp.innovrex.showcase.tokens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalTransitions
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val TransitionTokensShowcase = ComponentItem(
    name = "Transitions",
    description = "Screen & element transition patterns (slide, fade, shared axis).",
    category = ComponentCategory.TOKEN,
    preview = { TransitionPreview() },
    controls = { TransitionDescription() }
)

@Composable
private fun TransitionPreview() {
    val transitions = LocalTransitions.current
    var visible by remember { mutableStateOf(true) }
    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(onClick = { visible = !visible }) { Text(if (visible) "Hide" else "Show") }
        AnimatedVisibility(
            visibleState = remember { MutableTransitionState(true).apply { targetState = visible } },
            enter = transitions.enterFade(),
            exit = transitions.exitFade()
        ) {
            Box(Modifier.height(80.dp).fillMaxWidth()) {
                Text("Fading content", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
private fun TransitionDescription() {
    Text("Use TransitionTokens for consistent navigation and modal animations.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

