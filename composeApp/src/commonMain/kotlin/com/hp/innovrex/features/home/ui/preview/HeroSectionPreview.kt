package com.hp.innovrex.features.home.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.features.home.ui.components.HeroSection

/**
 * Preview component to showcase HeroSection at different breakpoints
 * Useful for design review and testing
 */
@Composable
fun HeroSectionPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandColors.Gray100)
            .padding(SpacingTokens.LG),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
    ) {
        Text(
            text = "Hero Section - Responsive Breakpoints",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = SpacingTokens.MD)
        )

        // Mobile Preview
        PreviewCard(
            title = "Mobile (< 768dp)",
            width = 375.dp
        ) {
            HeroSection(
                screenSize = ScreenSize.Mobile,
                onExploreClick = {},
                onContactClick = {}
            )
        }

        // Tablet Preview
        PreviewCard(
            title = "Tablet (768dp - 1024dp)",
            width = 768.dp
        ) {
            HeroSection(
                screenSize = ScreenSize.Tablet,
                onExploreClick = {},
                onContactClick = {}
            )
        }

        // Desktop Preview
        PreviewCard(
            title = "Desktop (1024dp - 1440dp)",
            width = 1200.dp
        ) {
            HeroSection(
                screenSize = ScreenSize.Desktop,
                onExploreClick = {},
                onContactClick = {}
            )
        }
    }
}

@Composable
private fun PreviewCard(
    title: String,
    width: Dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = SpacingTokens.SM)
        )

        Box(
            modifier = Modifier
                .width(width)
                .height(600.dp)
                .background(BrandColors.White),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

