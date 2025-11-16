package com.hp.innovrex.showcase.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.components.atoms.RIButton
import com.hp.innovrex.designsystem.tokens.RIButtonShape
import com.hp.innovrex.designsystem.tokens.RIButtonSize
import com.hp.innovrex.designsystem.tokens.RIButtonVariant
import com.hp.innovrex.designsystem.tokens.foundation.LocalSpacing
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

/**
 * RIButton showcase with interactive controls.
 */
val RIButtonShowcase = ComponentItem(
    name = "RIButton",
    description = "Multi-variant button component with support for different shapes, sizes, loading states, and icons.",
    category = ComponentCategory.ATOM,
    preview = { RIButtonPreview() },
    controls = { RIButtonControls() }
)

@Composable
private fun RIButtonPreview() {
    val state = remember { RIButtonShowcaseState() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RIButton(
            text = if (state.loading) "Loading..." else "Button Preview",
            onClick = { state.clickCount++ },
            variant = state.variant,
            shape = state.shape,
            size = state.size,
            enabled = state.enabled,
            loading = state.loading
        )

        if (state.clickCount > 0) {
            Text(
                "Clicked ${state.clickCount} time${if (state.clickCount != 1) "s" else ""}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RIButtonControls() {
    val state = remember { RIButtonShowcaseState() }
    val spacing = LocalSpacing.current

    Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
        // Variant selector
        ControlSection(title = "Variant") {
            listOf(
                "Filled" to RIButtonVariant.Filled,
                "Tonal" to RIButtonVariant.Tonal,
                "Outlined" to RIButtonVariant.Outlined,
                "Ghost" to RIButtonVariant.Ghost,
                "Link" to RIButtonVariant.Link,
                "Danger" to RIButtonVariant.Danger
            ).forEach { (label, variant) ->
                RadioButtonItem(
                    label = label,
                    selected = state.variant == variant,
                    onClick = { state.variant = variant }
                )
            }
        }

        HorizontalDivider()

        // Shape selector
        ControlSection(title = "Shape") {
            listOf(
                "Rectangle" to RIButtonShape.Rectangle,
                "Rounded" to RIButtonShape.Rounded,
                "Pill" to RIButtonShape.Pill,
                "Circle" to RIButtonShape.Circle
            ).forEach { (label, shape) ->
                RadioButtonItem(
                    label = label,
                    selected = state.shape == shape,
                    onClick = { state.shape = shape }
                )
            }
        }

        HorizontalDivider()

        // Size selector
        ControlSection(title = "Size") {
            listOf(
                "Small" to RIButtonSize.Small,
                "Medium" to RIButtonSize.Medium,
                "Large" to RIButtonSize.Large
            ).forEach { (label, size) ->
                RadioButtonItem(
                    label = label,
                    selected = state.size == size,
                    onClick = { state.size = size }
                )
            }
        }

        HorizontalDivider()

        // State toggles
        ControlSection(title = "State") {
            SwitchItem(
                label = "Enabled",
                checked = state.enabled,
                onCheckedChange = { state.enabled = it }
            )
            SwitchItem(
                label = "Loading",
                checked = state.loading,
                onCheckedChange = { state.loading = it }
            )
        }

        HorizontalDivider()

        // Reset button
        Button(
            onClick = { state.reset() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset to Defaults")
        }
    }
}

@Composable
private fun ControlSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier.selectableGroup(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun RadioButtonItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Spacer(Modifier.width(8.dp))
        Text(label)
    }
}

@Composable
private fun SwitchItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Stable
private class RIButtonShowcaseState {
    var variant by mutableStateOf<RIButtonVariant>(RIButtonVariant.Filled)
    var shape by mutableStateOf<RIButtonShape>(RIButtonShape.Rounded)
    var size by mutableStateOf<RIButtonSize>(RIButtonSize.Medium)
    var enabled by mutableStateOf(true)
    var loading by mutableStateOf(false)
    var clickCount by mutableIntStateOf(0)

    fun reset() {
        variant = RIButtonVariant.Filled
        shape = RIButtonShape.Rounded
        size = RIButtonSize.Medium
        enabled = true
        loading = false
        clickCount = 0
    }
}

