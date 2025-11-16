package com.hp.innovrex.components.atoms

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.map

// Brand color tokens (could be moved to a centralized theme file later)
object BrandColors {
    val RedPrimary = Color(0xFFD60000)
    val RedPrimaryDark = Color(0xFF9E0000)
    val RedPrimaryLight = Color(0xFFFF5252)
    val OnRedPrimary = Color.White
    val RedContainer = Color(0xFFFFE5E5)
    val OnRedContainer = Color(0xFF410000)
}

// Variants
sealed interface RIButtonVariant {
    data object Filled : RIButtonVariant
    data object Tonal : RIButtonVariant
    data object Outlined : RIButtonVariant
    data object Ghost : RIButtonVariant
    data object Link : RIButtonVariant
    data object Danger : RIButtonVariant // Explicit danger styling
}

// Shapes
sealed interface RIButtonShape {
    data object Rectangle : RIButtonShape
    data object Rounded : RIButtonShape
    data object Pill : RIButtonShape
    data object Circle : RIButtonShape // For icon-only or circular
}

// Sizes
sealed interface RIButtonSize {
    data object Small : RIButtonSize
    data object Medium : RIButtonSize
    data object Large : RIButtonSize
}

// Color model for button states
class RIButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color?,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val stateLayerColor: Color
)

// Metrics model
class RIButtonMetrics(
    val height: Dp,
    val horizontalPadding: Dp,
    val cornerShape: Shape,
    val textStyle: TextStyle,
    val iconSize: Dp,
    val spacing: Dp
)

object RIButtonDefaults {
    @Composable
    fun colors(variant: RIButtonVariant, enabled: Boolean): RIButtonColors {
        val scheme = MaterialTheme.colorScheme
        return when (variant) {
            RIButtonVariant.Filled -> RIButtonColors(
                containerColor = if (enabled) BrandColors.RedPrimary else scheme.surfaceVariant,
                contentColor = if (enabled) BrandColors.OnRedPrimary else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = scheme.surfaceVariant,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandColors.OnRedPrimary.copy(alpha = 0.12f)
            )
            RIButtonVariant.Tonal -> RIButtonColors(
                containerColor = if (enabled) BrandColors.RedContainer else scheme.surfaceVariant,
                contentColor = if (enabled) BrandColors.OnRedContainer else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = scheme.surfaceVariant,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandColors.OnRedContainer.copy(alpha = 0.12f)
            )
            RIButtonVariant.Outlined -> RIButtonColors(
                containerColor = Color.Transparent,
                contentColor = if (enabled) BrandColors.RedPrimary else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = if (enabled) BrandColors.RedPrimary else scheme.outline.copy(alpha = 0.38f),
                disabledContainerColor = Color.Transparent,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandColors.RedPrimary.copy(alpha = 0.12f)
            )
            RIButtonVariant.Ghost -> RIButtonColors(
                containerColor = Color.Transparent,
                contentColor = if (enabled) BrandColors.RedPrimary else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandColors.RedPrimary.copy(alpha = 0.08f)
            )
            RIButtonVariant.Link -> RIButtonColors(
                containerColor = Color.Transparent,
                contentColor = if (enabled) BrandColors.RedPrimary else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = Color.Transparent
            )
            RIButtonVariant.Danger -> RIButtonColors(
                containerColor = if (enabled) BrandColors.RedPrimaryDark else scheme.surfaceVariant,
                contentColor = if (enabled) BrandColors.OnRedPrimary else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = scheme.surfaceVariant,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandColors.OnRedPrimary.copy(alpha = 0.16f)
            )
        }
    }

    @Composable
    fun metrics(size: RIButtonSize, shape: RIButtonShape): RIButtonMetrics {
        return when (size) {
            RIButtonSize.Small -> RIButtonMetrics(32.dp, 12.dp, rectangleShapeFor(shape, 32.dp), TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Medium), 16.dp, 6.dp)
            RIButtonSize.Medium -> RIButtonMetrics(40.dp, 16.dp, rectangleShapeFor(shape, 40.dp), TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium), 20.dp, 8.dp)
            RIButtonSize.Large -> RIButtonMetrics(48.dp, 20.dp, rectangleShapeFor(shape, 48.dp), TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold), 24.dp, 10.dp)
        }
    }

    private fun rectangleShapeFor(shape: RIButtonShape, height: Dp): Shape = when (shape) {
        RIButtonShape.Rectangle -> RoundedCornerShape(0.dp)
        RIButtonShape.Rounded -> RoundedCornerShape(8.dp)
        RIButtonShape.Pill -> RoundedCornerShape(height / 2)
        RIButtonShape.Circle -> CircleShape
    }
}

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
