package com.hp.innovrex.features.aboutus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue

/**
 * About Us Section Component
 * Displays company information with team image
 */
@Composable
fun AboutUsSection(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop
) {
    val padding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        screenSize = screenSize
    )

    val isMobile = screenSize == ScreenSize.Mobile

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.White)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        if (isMobile) {
            // Mobile: Stacked layout
            MobileAboutUsLayout()
        } else {
            // Desktop/Tablet: Side-by-side layout
            DesktopAboutUsLayout()
        }
    }
}

@Composable
private fun MobileAboutUsLayout() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
    ) {
        AboutUsContent()

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        TeamImage(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun DesktopAboutUsLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.Huge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side: Content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            AboutUsContent()
        }

        // Right side: Image
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            TeamImage(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun AboutUsContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
    ) {
        // Title
        Text(
            text = "About Us",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            ),
            color = BrandColors.Gray900
        )

        Spacer(modifier = Modifier.height(SpacingTokens.SM))

        // First paragraph
        Text(
            text = "We are a Kotlin Multiplatform (KMP)-based software development company specializing in creating cross-platform applications that run seamlessly across mobile, web, and desktop environments.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray700
        )

        Spacer(modifier = Modifier.height(SpacingTokens.SM))

        // Second paragraph
        Text(
            text = "Our mission is to simplify development through a shared codebase, delivering consistent performance and experience across platforms — Android, iOS, Windows, and macOS.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray700
        )
    }
}

@Composable
private fun TeamImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.Gray200),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder for team image
        // TODO: Add actual team collaboration image resource
        Text(
            text = "Team Collaboration Image",
            style = MaterialTheme.typography.bodyMedium,
            color = BrandColors.Gray600
        )
    }
}

