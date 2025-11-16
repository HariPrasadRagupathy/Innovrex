package com.hp.innovrex.designsystem.tokens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.hp.innovrex.designsystem.tokens.foundation.BrandPalette

// Button variants define visual treatment
sealed interface RIButtonVariant {
    data object Filled : RIButtonVariant
    data object Tonal : RIButtonVariant
    data object Outlined : RIButtonVariant
    data object Ghost : RIButtonVariant
    data object Link : RIButtonVariant
    data object Danger : RIButtonVariant
}

// Button shapes
sealed interface RIButtonShape {
    data object Rectangle : RIButtonShape
    data object Rounded : RIButtonShape
    data object Pill : RIButtonShape
    data object Circle : RIButtonShape
}

// Button sizes for consistent ergonomics
sealed interface RIButtonSize {
    data object Small : RIButtonSize
    data object Medium : RIButtonSize
    data object Large : RIButtonSize
}

// Immutable color set resolved per variant + state
class RIButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color?,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val stateLayerColor: Color
)

// Immutable metrics set resolved per size + shape
class RIButtonMetrics(
    val height: Dp,
    val horizontalPadding: Dp,
    val cornerShape: Shape,
    val textStyle: TextStyle,
    val iconSize: Dp,
    val spacing: Dp
)

// Defaults/mappers separated for testability
object RIButtonDefaults {
    @Composable
    fun colors(variant: RIButtonVariant, enabled: Boolean): RIButtonColors {
        val scheme = MaterialTheme.colorScheme
        return when (variant) {
            RIButtonVariant.Filled -> RIButtonColors(
                containerColor = if (enabled) BrandPalette.PrimaryRed else scheme.surfaceVariant,
                contentColor = if (enabled) BrandPalette.OnPrimaryRed else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = scheme.surfaceVariant,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandPalette.OnPrimaryRed.copy(alpha = 0.12f)
            )
            RIButtonVariant.Tonal -> RIButtonColors(
                containerColor = if (enabled) BrandPalette.PrimaryRedContainer else scheme.surfaceVariant,
                contentColor = if (enabled) BrandPalette.OnPrimaryRedContainer else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = scheme.surfaceVariant,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandPalette.OnPrimaryRedContainer.copy(alpha = 0.12f)
            )
            RIButtonVariant.Outlined -> RIButtonColors(
                containerColor = Color.Transparent,
                contentColor = if (enabled) BrandPalette.PrimaryRed else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = if (enabled) BrandPalette.PrimaryRed else scheme.outline.copy(alpha = 0.38f),
                disabledContainerColor = Color.Transparent,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandPalette.PrimaryRed.copy(alpha = 0.12f)
            )
            RIButtonVariant.Ghost -> RIButtonColors(
                containerColor = Color.Transparent,
                contentColor = if (enabled) BrandPalette.PrimaryRed else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandPalette.PrimaryRed.copy(alpha = 0.08f)
            )
            RIButtonVariant.Link -> RIButtonColors(
                containerColor = Color.Transparent,
                contentColor = if (enabled) BrandPalette.PrimaryRed else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = Color.Transparent
            )
            RIButtonVariant.Danger -> RIButtonColors(
                containerColor = if (enabled) BrandPalette.PrimaryRedDark else scheme.surfaceVariant,
                contentColor = if (enabled) BrandPalette.OnPrimaryRed else scheme.onSurface.copy(alpha = 0.38f),
                borderColor = null,
                disabledContainerColor = scheme.surfaceVariant,
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f),
                stateLayerColor = BrandPalette.OnPrimaryRed.copy(alpha = 0.16f)
            )
        }
    }

    @Composable
    fun metrics(size: RIButtonSize, shape: RIButtonShape): RIButtonMetrics = when (size) {
        RIButtonSize.Small -> RIButtonMetrics(32.dp, 12.dp, rectangleShapeFor(shape, 32.dp), TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Medium), 16.dp, 6.dp)
        RIButtonSize.Medium -> RIButtonMetrics(40.dp, 16.dp, rectangleShapeFor(shape, 40.dp), TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium), 20.dp, 8.dp)
        RIButtonSize.Large -> RIButtonMetrics(48.dp, 20.dp, rectangleShapeFor(shape, 48.dp), TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold), 24.dp, 10.dp)
    }

    private fun rectangleShapeFor(shape: RIButtonShape, height: Dp): Shape = when (shape) {
        RIButtonShape.Rectangle -> RoundedCornerShape(0.dp)
        RIButtonShape.Rounded -> RoundedCornerShape(8.dp)
        RIButtonShape.Pill -> RoundedCornerShape(height / 2)
        RIButtonShape.Circle -> CircleShape
    }
}
