package com.hp.innovrex.features.whyrexinnov.ui

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
 * Why Rexinnov Section - The Rexinnov Advantage
 * Displays codebase advantages and expert team support
 */
@Composable
fun WhyRexinnovSection(
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
            .background(BrandColors.DarkBackground)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        // Section Header
        SectionHeader()

        Spacer(modifier = Modifier.height(SpacingTokens.Huge))

        // Advantage Cards
        if (isMobile) {
            MobileAdvantageLayout()
        } else {
            DesktopAdvantageLayout()
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
            text = "The Rexinnov Advantage",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),
            color = BrandColors.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(SpacingTokens.MD))

        Text(
            text = "We combine the power of a unified codebase with the strength of an expert team to deliver unparalleled value and efficiency.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray300,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 800.dp)
        )
    }
}

@Composable
private fun MobileAdvantageLayout() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
    ) {
        CodebaseAdvantagesCard()
        ExpertTeamSupportCard()
    }
}

@Composable
private fun DesktopAdvantageLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            CodebaseAdvantagesCard()
        }
        Box(modifier = Modifier.weight(1f)) {
            ExpertTeamSupportCard()
        }
    }
}

