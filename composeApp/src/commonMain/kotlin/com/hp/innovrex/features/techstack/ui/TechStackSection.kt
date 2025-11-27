package com.hp.innovrex.features.techstack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue

/**
 * Technology Stack & Platforms Section
 * Displays the technologies and platforms used in development
 */
@Composable
fun TechStackSection(
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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.White)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        // Section Header
        SectionHeader()

        Spacer(modifier = Modifier.height(SpacingTokens.Huge))

        // Content Layout
        if (isMobile) {
            MobileTechStackLayout()
        } else {
            DesktopTechStackLayout()
        }
    }
}

@Composable
private fun SectionHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Technology Stack & Platforms",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            ),
            color = BrandColors.Gray900,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(SpacingTokens.MD))

        Text(
            text = "We leverage the best of the Kotlin ecosystem to build and test cross-platform apps on all platforms.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray600,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 800.dp)
        )
    }
}

@Composable
private fun MobileTechStackLayout() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
    ) {
        TechnologyList()
        Spacer(modifier = Modifier.height(SpacingTokens.LG))
        SupportedPlatforms()
    }
}

@Composable
private fun DesktopTechStackLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.Huge)
    ) {
        // Left side - Technology List
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            TechnologyList()
        }

        // Right side - Supported Platforms
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            SupportedPlatforms()
        }
    }
}

