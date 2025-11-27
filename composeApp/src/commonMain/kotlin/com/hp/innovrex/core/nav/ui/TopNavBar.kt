package com.hp.innovrex.core.nav.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.components.PrimaryButton
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue

/**
 * Navigation item data class
 */
data class NavItem(
    val label: String,
    val sectionId: String
)

/**
 * Top navigation bar component
 * Responsive navigation with logo, menu items, and CTA button
 */
@Composable
fun TopNavBar(
    modifier: Modifier = Modifier,
    screenSize: ScreenSize = ScreenSize.Desktop,
    onNavigate: (String) -> Unit = {},
    onCtaClick: () -> Unit = {}
) {
    val isDesktop = screenSize >= ScreenSize.Desktop
    var isMenuOpen by remember { mutableStateOf(false) }

    val navItems = remember {
        listOf(
            NavItem("Home", "home"),
            NavItem("About", "about"),
            NavItem("Services", "services"),
            NavItem("Products", "products"),
            NavItem("Contact", "contact")
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BrandColors.White)
            .padding(
                horizontal = responsiveValue(
                    mobile = SpacingTokens.LG,
                    tablet = SpacingTokens.XXL,
                    desktop = SpacingTokens.Giant,
                    screenSize = screenSize
                ),
                vertical = SpacingTokens.LG
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Logo()

            if (isDesktop) {
                // Desktop Navigation
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpacingTokens.XL),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEach { item ->
                        NavMenuItem(
                            label = item.label,
                            onClick = { onNavigate(item.sectionId) }
                        )
                    }

                    PrimaryButton(
                        text = "Get Started",
                        onClick = onCtaClick,
                        modifier = Modifier.height(40.dp)
                    )
                }
            } else {
                // Mobile Menu Button
                MenuButton(
                    isOpen = isMenuOpen,
                    onClick = { isMenuOpen = !isMenuOpen }
                )
            }
        }

        // Mobile Menu Dropdown
        if (!isDesktop && isMenuOpen) {
            MobileMenu(
                navItems = navItems,
                onNavigate = { sectionId ->
                    onNavigate(sectionId)
                    isMenuOpen = false
                },
                onCtaClick = {
                    onCtaClick()
                    isMenuOpen = false
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(y = 60.dp)
            )
        }
    }
}

/**
 * Logo component matching rexinnov branding
 * Displays the red blob shape with white text
 */
@Composable
private fun Logo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.XS)
    ) {
        // Red blob background with text overlay
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(56.dp),
            contentAlignment = Alignment.Center
        ) {
            // Background blob shape (simplified rounded rectangle for now)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = BrandColors.Red600,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(
                            topStart = 28.dp,
                            topEnd = 40.dp,
                            bottomStart = 40.dp,
                            bottomEnd = 28.dp
                        )
                    )
            )

            // Logo text and tagline
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Main logo text "rexinnov"
                Text(
                    text = "rexinnov",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = (-0.3).sp
                    ),
                    color = BrandColors.White
                )

                // Tagline
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Built by Ideas.",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 7.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = BrandColors.White.copy(alpha = 0.95f)
                    )
                    Text(
                        text = "Driven by Passion.",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 7.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = BrandColors.White.copy(alpha = 0.95f)
                    )
                }
            }
        }
    }
}

/**
 * Navigation menu item (text link)
 */
@Composable
private fun NavMenuItem(
    label: String,
    onClick: () -> Unit
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge,
        color = BrandColors.Gray900,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = SpacingTokens.SM, horizontal = SpacingTokens.MD)
    )
}

/**
 * Mobile menu button (hamburger icon)
 */
@Composable
private fun MenuButton(
    isOpen: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(2.dp)
                        .background(BrandColors.Gray900)
                )
            }
        }
    }
}

/**
 * Mobile dropdown menu
 */
@Composable
private fun MobileMenu(
    navItems: List<NavItem>,
    onNavigate: (String) -> Unit,
    onCtaClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(BrandColors.DarkSurface)
            .padding(SpacingTokens.LG)
            .widthIn(min = 200.dp),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
    ) {
        navItems.forEach { item ->
            Text(
                text = item.label,
                style = MaterialTheme.typography.bodyLarge,
                color = BrandColors.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(item.sectionId) }
                    .padding(vertical = SpacingTokens.SM)
            )
        }

        Spacer(modifier = Modifier.height(SpacingTokens.SM))

        PrimaryButton(
            text = "Get Started",
            onClick = onCtaClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

