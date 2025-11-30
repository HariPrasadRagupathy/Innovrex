package com.hp.innovrex.features.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.components.PrimaryButton
import com.hp.innovrex.designsystem.components.SecondaryButton
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue

/**
 * Hero section component for the landing page
 * Responsive layout that adapts to different screen sizes
 */
@Composable
fun HeroSection(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop,
    onExploreClick: () -> Unit = {},
    onContactClick: () -> Unit = {}
) {
    val isDesktop = screenSize >= ScreenSize.Desktop

    // Responsive padding and spacing
    val horizontalPadding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        wide = 160.dp,
        screenSize = screenSize
    )

    val verticalPadding = responsiveValue(
        mobile = SpacingTokens.XL,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Huge,
        wide = 80.dp,
        screenSize = screenSize
    )

    val contentMaxWidth = responsiveValue(
        mobile = 600.dp,
        tablet = 700.dp,
        desktop = 900.dp,
        wide = 1200.dp,
        screenSize = screenSize
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = responsiveValue(
                mobile = 500.dp,
                tablet = 600.dp,
                desktop = 700.dp,
                wide = 800.dp,
                screenSize = screenSize
            ))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0A0A),
                        Color.Black,
                        Color.Black
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Background pattern
        Canvas(modifier = Modifier.fillMaxSize()) {
            val dotSpacing = 40f
            val dotRadius = 1.5f
            val dotColor = Color.White.copy(alpha = 0.08f)

            val columns = (size.width / dotSpacing).toInt()
            val rows = (size.height / dotSpacing).toInt()

            for (i in 0..columns) {
                for (j in 0..rows) {
                    val x = i * dotSpacing
                    val y = j * dotSpacing
                    drawCircle(
                        color = dotColor,
                        radius = dotRadius,
                        center = Offset(x, y)
                    )
                }
            }
        }

        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = verticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Main headline
            Text(
                text = "Full-Stack Multiplatform Product Development",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = responsiveValue(
                        mobile = 32.sp,
                        tablet = 45.sp,
                        desktop = 57.sp,
                        wide = 68.sp,
                        screenSize = screenSize
                    ),
                    fontWeight = FontWeight.Bold,
                    lineHeight = responsiveValue(
                        mobile = 40.sp,
                        tablet = 52.sp,
                        desktop = 64.sp,
                        wide = 80.sp,
                        screenSize = screenSize
                    )
                ),
                color = BrandColors.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = contentMaxWidth)
            )

            Spacer(modifier = Modifier.height(SpacingTokens.LG))

            // Subtitle
            Text(
                text = "One Kotlin codebase, multiple experiences. We build high-performance mobile, web, desktop, and server REST API solutions.",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = responsiveValue(
                        mobile = 16.sp,
                        tablet = 18.sp,
                        desktop = 20.sp,
                        wide = 22.sp,
                        screenSize = screenSize
                    ),
                    fontWeight = FontWeight.Normal,
                    lineHeight = responsiveValue(
                        mobile = 24.sp,
                        tablet = 28.sp,
                        desktop = 32.sp,
                        wide = 34.sp,
                        screenSize = screenSize
                    )
                ),
                color = BrandColors.Gray300,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = contentMaxWidth * 0.85f)
            )

            Spacer(modifier = Modifier.height(SpacingTokens.XXL))

            // CTA Buttons
            if (isDesktop) {
                // Horizontal layout for desktop
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
                    modifier = Modifier.padding(top = SpacingTokens.MD)
                ) {
                    PrimaryButton(
                        text = "Explore Our Solutions",
                        onClick = onExploreClick,
                        modifier = Modifier.widthIn(min = 200.dp).height(48.dp)
                    )

                    SecondaryButton(
                        text = "Contact Us",
                        onClick = onContactClick,
                        modifier = Modifier.widthIn(min = 160.dp).height(48.dp)
                    )
                }
            } else {
                // Vertical layout for mobile/tablet
                Column(
                    verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SpacingTokens.MD)
                ) {
                    PrimaryButton(
                        text = "Explore Our Solutions",
                        onClick = onExploreClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 400.dp)
                            .height(48.dp)
                    )

                    SecondaryButton(
                        text = "Contact Us",
                        onClick = onContactClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 400.dp)
                            .height(48.dp)
                    )
                }
            }
        }
    }
}

