package com.hp.innovrex.features.services.ui

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
 * Our Services Section Component
 * Displays all service offerings with detailed cards
 */
@Composable
fun ServicesSection(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop
) {
    val padding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        screenSize = screenSize
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.Gray50)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        // Section Header
        SectionHeader()

        Spacer(modifier = Modifier.height(SpacingTokens.Huge))

        // Service Cards
        ServiceCardsGrid(screenSize = screenSize)
    }
}

@Composable
private fun SectionHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Our Services",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),
            color = BrandColors.Gray900,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(SpacingTokens.MD))

        Text(
            text = "End-to-end Kotlin Multiplatform development services to create innovative solutions at platforms.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray600,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 700.dp)
        )
    }
}

@Composable
private fun ServiceCardsGrid(screenSize: ScreenSize) {
    val isMobile = screenSize == ScreenSize.Mobile

    if (isMobile) {
        // Mobile: Stacked layout
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
        ) {
            CrossPlatformAppDevelopmentCard(screenSize)
            CustomSoftwareSolutionsCard(screenSize)
            ConsultingArchitectureCard(screenSize)
            TrainingWorkshopsCard(screenSize)
        }
    } else {
        // Desktop/Tablet: Grid layout
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
        ) {
            CrossPlatformAppDevelopmentCard(screenSize)
            CustomSoftwareSolutionsCard(screenSize)
            ConsultingArchitectureCard(screenSize)
            TrainingWorkshopsCard(screenSize)
        }
    }
}

