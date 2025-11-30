package com.hp.innovrex.core.nav.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.components.PrimaryButton
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import com.hp.innovrex.designsystem.utils.responsiveValue
import innovrex.composeapp.generated.resources.Res
import innovrex.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

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
    currentSection: String = "home",
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
            NavItem("Tech Stack", "techstack"),
            NavItem("Why Rexinnov?", "whyrexinnov"),
            NavItem("Products", "products")
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
                vertical = SpacingTokens.SM
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Logo(screenSize)

            if (isDesktop) {
                // Desktop Navigation
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEach { item ->
                        NavMenuItem(
                            label = item.label,
                            isActive = currentSection == item.sectionId,
                            onClick = { onNavigate(item.sectionId) }
                        )
                    }

                    PrimaryButton(
                        text = "Get Started",
                        onClick = onCtaClick,
                        modifier = Modifier.height(32.dp)
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
private fun Logo(screenSize: ScreenSize = ScreenSize.Desktop) {
    val logoHeight = responsiveValue(
        mobile = 48.dp,
        tablet = 56.dp,
        desktop = 100.dp,
        screenSize = screenSize
    )

    Image(
        painter = painterResource(Res.drawable.logo),
        contentDescription = "Rexinnov Logo",
        modifier = Modifier.height(logoHeight),
        contentScale = ContentScale.Fit
    )
}

/**
 * Navigation menu item (text link)
 * With hover effect and active state
 */
@Composable
private fun NavMenuItem(
    label: String,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor = when {
        isActive -> BrandColors.Red600.copy(alpha = 0.1f)
        isHovered -> BrandColors.Gray100
        else -> BrandColors.White
    }

    val textColor = if (isActive) BrandColors.Red600 else BrandColors.Gray900

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .hoverable(interactionSource = interactionSource)
            .padding(vertical = SpacingTokens.SM, horizontal = SpacingTokens.MD)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
            ),
            color = textColor
        )
    }
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

