package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

// Standard shape scale for Innovrex (Material3 style).
object ShapeTokens {
    val Small = RoundedCornerShape(4.dp)
    val Medium = RoundedCornerShape(8.dp)
    val Large = RoundedCornerShape(16.dp)

    val MaterialShapes = Shapes(
        small = Small,
        medium = Medium,
        large = Large,
    )
}

