package com.hp.innovrex.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.theme.InnovrexTheme
import com.hp.innovrex.designsystem.tokens.foundation.LocalSpacing

@Composable
internal fun ShowcaseContent(onClose: () -> Unit) {
    InnovrexTheme {
        ShowcaseScaffold(onClose)
    }
}

@Composable
private fun ShowcaseScaffold(onClose: () -> Unit) {
    var selectedComponent by remember { mutableStateOf<ComponentItem?>(null) }

    Row(Modifier.fillMaxSize()) {
        // Left sidebar - Component list
        ComponentList(
            modifier = Modifier.width(280.dp).fillMaxHeight(),
            onComponentSelected = { selectedComponent = it },
            selectedComponent = selectedComponent
        )

        // Right content - Component showcase
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            selectedComponent?.let { component ->
                ComponentShowcaseView(component)
            } ?: EmptyState()
        }
    }
}

@Composable
private fun ComponentList(
    modifier: Modifier = Modifier,
    onComponentSelected: (ComponentItem) -> Unit,
    selectedComponent: ComponentItem?
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(spacing.md)
    ) {
        Text(
            "Design System Showcase",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = spacing.lg)
        )

        Divider(Modifier.padding(bottom = spacing.md))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(spacing.xs)
        ) {
            item {
                Text(
                    "ATOMS",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = spacing.sm)
                )
            }

            items(ComponentRegistry.atoms) { component ->
                ComponentListItem(
                    component = component,
                    isSelected = component == selectedComponent,
                    onClick = { onComponentSelected(component) }
                )
            }

            item {
                Spacer(Modifier.height(spacing.md))
                Text(
                    "MOLECULES",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = spacing.sm)
                )
            }

            items(ComponentRegistry.molecules) { component ->
                ComponentListItem(
                    component = component,
                    isSelected = component == selectedComponent,
                    onClick = { onComponentSelected(component) }
                )
            }

            item {
                Spacer(Modifier.height(spacing.md))
                Text(
                    "ORGANISMS",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = spacing.sm)
                )
            }

            items(ComponentRegistry.organisms) { component ->
                ComponentListItem(
                    component = component,
                    isSelected = component == selectedComponent,
                    onClick = { onComponentSelected(component) }
                )
            }

            item {
                Spacer(Modifier.height(spacing.md))
                Text(
                    "TOKENS",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = spacing.sm)
                )
            }

            items(ComponentRegistry.tokens) { component ->
                ComponentListItem(
                    component = component,
                    isSelected = component == selectedComponent,
                    onClick = { onComponentSelected(component) }
                )
            }
        }
    }
}

@Composable
private fun ComponentListItem(
    component: ComponentItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.md, vertical = spacing.sm)
    ) {
        Text(
            component.name,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
            else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun ComponentShowcaseView(component: ComponentItem) {
    Column(Modifier.fillMaxSize()) {
        // Preview area (top 60%)
        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            component.preview()
        }

        // Controls area (bottom 40%)
        Surface(
            modifier = Modifier.weight(0.4f).fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Text(
                    component.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    component.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                component.controls()
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Select a component to preview",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

