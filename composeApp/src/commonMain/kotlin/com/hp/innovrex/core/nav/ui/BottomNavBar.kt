package com.hp.innovrex.core.nav.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue

/**
 * Footer Navigation Data
 */
data class FooterLink(
    val label: String,
    val sectionId: String = ""
)

/**
 * Bottom Navigation Bar / Footer
 * Displays company info, services, products, and company links
 */
@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop,
    onNavigate: (String) -> Unit = {}
) {
    val padding = responsiveValue(
        mobile = SpacingTokens.LG,
        tablet = SpacingTokens.XXL,
        desktop = SpacingTokens.Giant,
        screenSize = screenSize
    )

    val isMobile = screenSize == ScreenSize.Mobile
    val isTablet = screenSize == ScreenSize.Tablet

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.DarkBackground)
            .padding(horizontal = padding, vertical = SpacingTokens.LG)
    ) {
        // Main Footer Content
        if (isMobile) {
            MobileFooterLayout(onNavigate)
        } else if (isTablet) {
            TabletFooterLayout(onNavigate)
        } else {
            DesktopFooterLayout(onNavigate)
        }

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        // Copyright
        CopyrightSection()
    }
}

@Composable
private fun MobileFooterLayout(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
    ) {
        FooterBranding()
        FooterServicesColumn(onNavigate)
        FooterProductsColumn(onNavigate)
        FooterCompanyColumn(onNavigate)
    }
}

@Composable
private fun TabletFooterLayout(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXL)
    ) {
        FooterBranding()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                FooterServicesColumn(onNavigate)
            }
            Box(modifier = Modifier.weight(1f)) {
                FooterProductsColumn(onNavigate)
            }
            Box(modifier = Modifier.weight(1f)) {
                FooterCompanyColumn(onNavigate)
            }
        }
    }
}

@Composable
private fun DesktopFooterLayout(onNavigate: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left: Branding
        Box(modifier = Modifier.weight(2f)) {
            FooterBranding()
        }

        // Right: Links columns
        Row(
            modifier = Modifier.weight(3f),
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.Huge)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                FooterServicesColumn(onNavigate)
            }
            Box(modifier = Modifier.weight(1f)) {
                FooterProductsColumn(onNavigate)
            }
            Box(modifier = Modifier.weight(1f)) {
                FooterCompanyColumn(onNavigate)
            }
        }
    }
}

@Composable
private fun FooterBranding() {
    Column(
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.SM)
    ) {
        // Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.SM)
        ) {
            Text(
                text = "🚀",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 24.sp
                )
            )
            Text(
                text = "rexinnov",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = BrandColors.White
            )
        }

        // Tagline
        Text(
            text = "Kotlin Multiplatform Software Solutions",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp
            ),
            color = BrandColors.Gray400
        )
    }
}

@Composable
private fun FooterServicesColumn(onNavigate: (String) -> Unit) {
    FooterLinksColumn(
        title = "Services",
        links = listOf(
            FooterLink("App Development", "services"),
            FooterLink("Custom Solutions", "services"),
            FooterLink("Consulting", "services"),
            FooterLink("Training", "services")
        ),
        onNavigate = onNavigate
    )
}

@Composable
private fun FooterProductsColumn(onNavigate: (String) -> Unit) {
    FooterLinksColumn(
        title = "Products",
        links = listOf(
            FooterLink("Business Framework", "products"),
            FooterLink("CrossLearn", "products"),
            FooterLink("UI Components", "products")
        ),
        onNavigate = onNavigate
    )
}

@Composable
private fun FooterCompanyColumn(onNavigate: (String) -> Unit) {
    FooterLinksColumn(
        title = "Company",
        links = listOf(
            FooterLink("About Us", "about"),
            FooterLink("Tech Stack", "techstack"),
            FooterLink("Contact", "contact")
        ),
        onNavigate = onNavigate
    )
}

@Composable
private fun FooterLinksColumn(
    title: String,
    links: List<FooterLink>,
    onNavigate: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
    ) {
        // Column Title
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ),
            color = BrandColors.White
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XS))

        // Links
        links.forEach { link ->
            Text(
                text = link.label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                color = BrandColors.Gray400,
                modifier = Modifier.clickable {
                    if (link.sectionId.isNotEmpty()) {
                        onNavigate(link.sectionId)
                    }
                }
            )
        }
    }
}

@Composable
private fun CopyrightSection() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "© 2024 Rexinnov. All rights reserved.",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 13.sp
            ),
            color = BrandColors.Gray500
        )
    }
}

