package com.hp.innovrex.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        mobile = SpacingTokens.XXL,
        tablet = SpacingTokens.Huge,
        desktop = 120.dp,
        wide = 160.dp,
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BrandColors.DarkBackground,
                        BrandColors.DarkSurface,
                        BrandColors.DarkBackground
                    )
                )
            )
    ) {
        // Decorative background elements (simulating the network pattern)
        BackgroundPattern()

        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Main headline
            Text(
                text = "Kotlin Multiplatform Software Solutions",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = responsiveValue(
                        mobile = 32.sp,
                        tablet = 45.sp,
                        desktop = 57.sp,
                        wide = 72.sp,
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
                text = "One codebase, multiple platforms. We build seamless, high-performance applications for mobile, web, and desktop.",
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
                        modifier = Modifier.widthIn(min = 200.dp)
                    )

                    SecondaryButton(
                        text = "Contact Us",
                        onClick = onContactClick,
                        modifier = Modifier.widthIn(min = 160.dp)
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
                    )

                    SecondaryButton(
                        text = "Contact Us",
                        onClick = onContactClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 400.dp)
                    )
                }
            }
        }
    }
}

/**
 * Background pattern component
 * Creates a subtle network/grid pattern effect
 */
@Composable
private fun BoxScope.BackgroundPattern() {
    // Add decorative red dots and lines (simplified version)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        BrandColors.Red900.copy(alpha = 0.1f),
                        Color.Transparent
                    ),
                    center = androidx.compose.ui.geometry.Offset(0.2f, 0.3f)
                )
            )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        BrandColors.Red800.copy(alpha = 0.15f),
                        Color.Transparent
                    ),
                    center = androidx.compose.ui.geometry.Offset(0.8f, 0.6f)
                )
            )
    )
}

