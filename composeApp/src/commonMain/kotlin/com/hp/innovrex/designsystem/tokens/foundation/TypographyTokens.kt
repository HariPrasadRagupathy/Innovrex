package com.hp.innovrex.designsystem.tokens.foundation

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Centralized typography tokens for Innovrex.
// Replace default FontFamily with a custom one when available.
object TypographyTokens {
    private val BaseFontFamily: FontFamily = FontFamily.Default

    // Display
    val DisplayLarge = TextStyle(fontSize = 57.sp, lineHeight = 64.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Normal)
    val DisplayMedium = TextStyle(fontSize = 45.sp, lineHeight = 52.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Normal)
    val DisplaySmall = TextStyle(fontSize = 36.sp, lineHeight = 44.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Normal)

    // Headline
    val HeadlineLarge = TextStyle(fontSize = 32.sp, lineHeight = 40.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.SemiBold)
    val HeadlineMedium = TextStyle(fontSize = 28.sp, lineHeight = 36.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.SemiBold)
    val HeadlineSmall = TextStyle(fontSize = 24.sp, lineHeight = 32.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.SemiBold)

    // Title
    val TitleLarge = TextStyle(fontSize = 22.sp, lineHeight = 28.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Medium)
    val TitleMedium = TextStyle(fontSize = 16.sp, lineHeight = 24.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Medium)
    val TitleSmall = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Medium)

    // Body
    val BodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 24.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Normal)
    val BodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Normal)
    val BodySmall = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Normal)

    // Label
    val LabelLarge = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Medium)
    val LabelMedium = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Medium)
    val LabelSmall = TextStyle(fontSize = 11.sp, lineHeight = 16.sp, fontFamily = BaseFontFamily, fontWeight = FontWeight.Medium)

    // Material3 Typography mapping
    val MaterialTypography = Typography(
        displayLarge = DisplayLarge,
        displayMedium = DisplayMedium,
        displaySmall = DisplaySmall,
        headlineLarge = HeadlineLarge,
        headlineMedium = HeadlineMedium,
        headlineSmall = HeadlineSmall,
        titleLarge = TitleLarge,
        titleMedium = TitleMedium,
        titleSmall = TitleSmall,
        bodyLarge = BodyLarge,
        bodyMedium = BodyMedium,
        bodySmall = BodySmall,
        labelLarge = LabelLarge,
        labelMedium = LabelMedium,
        labelSmall = LabelSmall,
    )
}
