package com.hp.innovrex.showcase

import androidx.compose.animation.core.animateDpAsState
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
    var darkTheme by remember { mutableStateOf(false) }
    InnovrexTheme(darkTheme = darkTheme) {
        ShowcaseScaffold(
            onClose = onClose,
            darkTheme = darkTheme,
            onToggleDarkTheme = { darkTheme = !darkTheme }
        )
    }
}

@Composable
private fun ShowcaseScaffold(
    onClose: () -> Unit,
    darkTheme: Boolean,
    onToggleDarkTheme: () -> Unit
) {
    var selectedComponent by remember { mutableStateOf<ComponentItem?>(null) }
    var sidebarCollapsed by remember { mutableStateOf(false) }
    val targetWidth = if (sidebarCollapsed) 48.dp else 280.dp
    val animatedWidth by animateDpAsState(targetValue = targetWidth)

    Row(Modifier.fillMaxSize()) {
        ComponentList(
            modifier = Modifier.width(animatedWidth).fillMaxHeight(),
            onComponentSelected = { selectedComponent = it },
            selectedComponent = selectedComponent,
            collapsed = sidebarCollapsed,
            onToggleCollapse = { sidebarCollapsed = !sidebarCollapsed },
            darkTheme = darkTheme,
            onToggleDarkTheme = onToggleDarkTheme
        )
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
    selectedComponent: ComponentItem?,
    collapsed: Boolean,
    onToggleCollapse: () -> Unit,
    darkTheme: Boolean,
    onToggleDarkTheme: () -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(if (collapsed) 4.dp else spacing.md)
    ) {
        // Header with collapse / expand toggle + theme toggle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 8.dp, horizontal = if (collapsed) 0.dp else 12.dp)
        ) {
            if (collapsed) {
                // Stacked small vertical actions
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Collapse toggle icon (uses text glyph)
                    Text(
                        text = ">",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.clickable { onToggleCollapse() }
                    )
                    Text(
                        text = if (darkTheme) "☾" else "☀",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.clickable { onToggleDarkTheme() }
                    )
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Design System Showcase",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.clickable { onToggleCollapse() }
                    )
                    IconButton(onClick = onToggleDarkTheme) {
                        Text(
                            if (darkTheme) "☾" else "☀",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
        if (collapsed) {
            Spacer(Modifier.height(12.dp))
            // Render minimal vertical list of first letters for quick navigation
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {
                items(ComponentRegistry.atoms) { component ->
                    CollapsedItem(shortLabel = component.name.first().uppercase(), isSelected = component == selectedComponent) {
                        onComponentSelected(component)
                    }
                }
                // We could add other categories similarly, but keep minimal for collapsed state
            }
        } else {
            Divider(Modifier.padding(bottom = spacing.md))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacing.xs)
            ) {
                item { SectionLabel("ATOMS") }
                items(ComponentRegistry.atoms) { component ->
                    ComponentListItem(
                        component = component,
                        isSelected = component == selectedComponent,
                        onClick = { onComponentSelected(component) }
                    )
                }
                item { Spacer(Modifier.height(spacing.md)); SectionLabel("MOLECULES") }
                items(ComponentRegistry.molecules) { component ->
                    ComponentListItem(
                        component = component,
                        isSelected = component == selectedComponent,
                        onClick = { onComponentSelected(component) }
                    )
                }
                item { Spacer(Modifier.height(spacing.md)); SectionLabel("ORGANISMS") }
                items(ComponentRegistry.organisms) { component ->
                    ComponentListItem(
                        component = component,
                        isSelected = component == selectedComponent,
                        onClick = { onComponentSelected(component) }
                    )
                }
                item { Spacer(Modifier.height(spacing.md)); SectionLabel("TOKENS") }
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
    // Ensure stable composition for the component with key
    key(component.name) {
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

@Composable
private fun SectionLabel(text: String) {
    val spacing = LocalSpacing.current
    Text(
        text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = spacing.sm)
    )
}

@Composable
private fun CollapsedItem(shortLabel: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            shortLabel,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
