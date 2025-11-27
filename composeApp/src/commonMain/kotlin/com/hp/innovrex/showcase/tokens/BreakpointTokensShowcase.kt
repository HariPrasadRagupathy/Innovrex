package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.LocalBreakpoints
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val BreakpointTokensShowcase = ComponentItem(
    name = "Breakpoints",
    description = "Responsive layout breakpoint scale.",
    category = ComponentCategory.TOKEN,
    preview = { BreakpointPreview() },
    controls = { BreakpointDescription() }
)

@Composable
private fun BreakpointPreview() {
    val bp = LocalBreakpoints.current
    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Mobile: ${bp.mobile} - ${bp.mobileLarge - 1}px", style = MaterialTheme.typography.bodySmall)
        Text("Mobile Large: >= ${bp.mobileLarge}px", style = MaterialTheme.typography.bodySmall)
        Text("Tablet: >= ${bp.tablet}px", style = MaterialTheme.typography.bodySmall)
        Text("Tablet Large: >= ${bp.tabletLarge}px", style = MaterialTheme.typography.bodySmall)
        Text("Desktop: >= ${bp.desktop}px", style = MaterialTheme.typography.bodySmall)
        Text("Desktop Large: >= ${bp.desktopLarge}px", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun BreakpointDescription() {
    Text("Breakpoints drive responsive column counts, spacing, and nav patterns.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

