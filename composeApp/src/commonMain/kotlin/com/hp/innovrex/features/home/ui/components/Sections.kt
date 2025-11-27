package com.hp.innovrex.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue

/**
 * Services section component
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

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.White)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
        ) {
            Text(
                text = "Our Services",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = BrandColors.Gray900
            )

            Text(
                text = "We provide comprehensive Kotlin Multiplatform development services",
                style = MaterialTheme.typography.bodyLarge,
                color = BrandColors.Gray700
            )

            Spacer(modifier = Modifier.height(SpacingTokens.XL))
        }
    }
}

/**
 * Products section component
 */
@Composable
fun ProductsSection(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop
) {
    val padding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        screenSize = screenSize
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.Gray100)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
        ) {
            Text(
                text = "Our Products",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = BrandColors.Gray900
            )

            Text(
                text = "Innovative solutions built with Kotlin Multiplatform",
                style = MaterialTheme.typography.bodyLarge,
                color = BrandColors.Gray700
            )

            Spacer(modifier = Modifier.height(SpacingTokens.XL))
        }
    }
}

/**
 * About section component
 */
@Composable
fun AboutSection(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop
) {
    val padding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        screenSize = screenSize
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.White)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
        ) {
            Text(
                text = "About Innovrex",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = BrandColors.Gray900
            )

            Text(
                text = "Leading the way in cross-platform development",
                style = MaterialTheme.typography.bodyLarge,
                color = BrandColors.Gray700
            )

            Spacer(modifier = Modifier.height(SpacingTokens.XL))
        }
    }
}

/**
 * Contact section component
 */
@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop
) {
    val padding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        screenSize = screenSize
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.DarkBackground)
            .padding(horizontal = padding, vertical = SpacingTokens.Huge)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
        ) {
            Text(
                text = "Get In Touch",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = BrandColors.White
            )

            Text(
                text = "Let's build something amazing together",
                style = MaterialTheme.typography.bodyLarge,
                color = BrandColors.Gray300
            )

            Spacer(modifier = Modifier.height(SpacingTokens.XL))
        }
    }
}

