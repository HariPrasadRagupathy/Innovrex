package com.hp.innovrex.components.atoms

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.RIButtonDefaults
import com.hp.innovrex.designsystem.tokens.RIButtonShape
import com.hp.innovrex.designsystem.tokens.RIButtonSize
import com.hp.innovrex.designsystem.tokens.RIButtonVariant
import kotlinx.coroutines.flow.map

@Composable
fun RIButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    variant: RIButtonVariant = RIButtonVariant.Filled,
    shape: RIButtonShape = RIButtonShape.Rounded,
    size: RIButtonSize = RIButtonSize.Medium,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    contentDescription: String? = null,
) {
    RIButtonInternal(
        modifier = modifier,
        onClick = onClick,
        variant = variant,
        shape = shape,
        size = size,
        enabled = enabled,
        loading = loading,
        contentDescription = contentDescription ?: text,
    ) {
        if (leading != null) {
            leading()
            Spacer(Modifier.width(RIButtonDefaults.metrics(size, shape).spacing))
        }
        Text(text, style = RIButtonDefaults.metrics(size, shape).textStyle)
        if (trailing != null) {
            Spacer(Modifier.width(RIButtonDefaults.metrics(size, shape).spacing))
            trailing()
        }
    }
}

@Composable
fun RIIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    variant: RIButtonVariant = RIButtonVariant.Filled,
    size: RIButtonSize = RIButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    contentDescription: String,
    icon: @Composable () -> Unit
) {
    RIButtonInternal(
        modifier = modifier,
        onClick = onClick,
        variant = variant,
        shape = RIButtonShape.Circle,
        size = size,
        enabled = enabled,
        loading = loading,
        contentDescription = contentDescription,
    ) { icon() }
}

@Composable
fun RIButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    variant: RIButtonVariant = RIButtonVariant.Filled,
    shape: RIButtonShape = RIButtonShape.Rounded,
    size: RIButtonSize = RIButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    contentDescription: String? = null,
    content: @Composable RowScope.() -> Unit
) {
    RIButtonInternal(
        modifier = modifier,
        onClick = onClick,
        variant = variant,
        shape = shape,
        size = size,
        enabled = enabled,
        loading = loading,
        contentDescription = contentDescription,
        content = content
    )
}

@Composable
private fun RIButtonInternal(
    modifier: Modifier,
    onClick: () -> Unit,
    variant: RIButtonVariant,
    shape: RIButtonShape,
    size: RIButtonSize,
    enabled: Boolean,
    loading: Boolean,
    contentDescription: String?,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val colors = RIButtonDefaults.colors(variant, enabled)
    val metrics = RIButtonDefaults.metrics(size, shape)

    // Collect hover/press/focus states (web & desktop; mobile press only)
    var pressed by remember { mutableStateOf(false) }
    var hovered by remember { mutableStateOf(false) }
    var focused by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.map { it }.collect { interaction ->
            // Basic classification via string matching (simplified to avoid extra dependency)
            val name = interaction::class.simpleName
            when {
                name?.contains("Press", ignoreCase = true) == true -> pressed = name.contains("Start", true)
                name?.contains("Hover", ignoreCase = true) == true -> hovered = name.contains("Enter", true)
                name?.contains("Focus", ignoreCase = true) == true -> focused = name.contains("Enter", true)
            }
        }
    }

    val stateLayerAlpha by animateFloatAsState(
        targetValue = when {
            pressed -> 0.16f
            hovered || focused -> 0.08f
            else -> 0f
        }, label = "stateLayerAlpha"
    )
    val containerColor by animateColorAsState(
        targetValue = if (enabled) colors.containerColor else colors.disabledContainerColor,
        label = "containerColor"
    )
    val contentColor by animateColorAsState(
        targetValue = if (enabled) colors.contentColor else colors.disabledContentColor,
        label = "contentColor"
    )

    val buttonModifier = modifier
        .height(metrics.height)
        .defaultMinSize(minHeight = metrics.height)
        .clip(metrics.cornerShape)
        .background(containerColor, metrics.cornerShape)
        .then(if (colors.borderColor != null) Modifier.border(1.dp, colors.borderColor, metrics.cornerShape) else Modifier)
        .clickable(enabled = enabled && !loading, interactionSource = interactionSource, indication = null) { onClick() }
        .padding(horizontal = metrics.horizontalPadding)
        .semantics { contentDescription?.let { this.contentDescription = it } } // simplified semantics usage

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Row(
            buttonModifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (loading) {
                // Reserve space for content; show small indicator
                val indicatorSize = metrics.iconSize
                Box(Modifier.size(indicatorSize), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        strokeWidth = 2.dp,
                        color = contentColor
                    )
                }
                Spacer(Modifier.width(metrics.spacing))
            }
            content()
            if (stateLayerAlpha > 0f) {
                Box(
                    Modifier
                        .fillMaxSize() // replaced matchParentSize
                        .clip(metrics.cornerShape)
                        .background(colors.stateLayerColor.copy(alpha = stateLayerAlpha))
                )
            }
        }
    }
}
