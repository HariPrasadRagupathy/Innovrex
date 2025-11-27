package com.hp.innovrex.features.contactus.ui

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
 * Contact Us Section - Get in Touch
 * Displays contact information and contact form
 */
@Composable
fun ContactUsSection(
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
            .background(BrandColors.Gray50)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        // Section Header
        SectionHeader()

        Spacer(modifier = Modifier.height(SpacingTokens.Huge))

        // Contact Content
        if (isMobile) {
            MobileContactLayout()
        } else {
            DesktopContactLayout()
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
            text = "Get in Touch",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),
            color = BrandColors.Gray900,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(SpacingTokens.MD))

        Text(
            text = "Have a project in mind or just want to say hello? We'd love to hear from you. Fill out the form below, and we'll get back to you as soon as possible.",
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
private fun MobileContactLayout() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
    ) {
        ContactInformationCard()
        ContactForm()
    }
}

@Composable
private fun DesktopContactLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            ContactInformationCard()
        }
        Box(modifier = Modifier.weight(1f)) {
            ContactForm()
        }
    }
}

