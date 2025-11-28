package com.hp.innovrex.features.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.hp.innovrex.core.nav.ui.TopNavBar
import com.hp.innovrex.core.nav.ui.BottomNavBar
import com.hp.innovrex.designsystem.utils.rememberScreenSize
import com.hp.innovrex.features.home.ui.components.HeroSection
import com.hp.innovrex.features.home.ui.components.ProductsSection
import com.hp.innovrex.features.aboutus.ui.AboutUsSection
import com.hp.innovrex.features.services.ui.ServicesSection
import com.hp.innovrex.features.techstack.ui.TechStackSection
import com.hp.innovrex.features.whyrexinnov.ui.WhyRexinnovSection
import com.hp.innovrex.features.contactus.ui.ContactUsSection
import kotlinx.coroutines.launch

/**
 * Home screen with responsive layout and navigation
 * Main entry point for the landing page
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onExploreClick: () -> Unit = {},
    onContactClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val density = LocalDensity.current
        val screenWidth = with(density) { maxWidth }
        val screenSize = rememberScreenSize(screenWidth)

        // Scroll state for programmatic scrolling
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        // Store section positions
        val sectionPositions = remember { mutableStateMapOf<String, Float>() }

        // Navbar height to offset scroll position
        val navBarHeight = with(density) { 56.dp.toPx() }

        // Handle navigation to sections
        val onNavigate: (String) -> Unit = { sectionId ->
            coroutineScope.launch {
                val position = sectionPositions[sectionId] ?: 0f
                // Position is now relative to parent (Column), just subtract navbar height
                val adjustedPosition = if (sectionId == "home") {
                    0
                } else {
                    (position - navBarHeight).coerceAtLeast(0f).toInt()
                }
                scrollState.animateScrollTo(adjustedPosition)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Add spacer for navbar height
                Spacer(modifier = Modifier.height(56.dp))

                // Hero Section (Home)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            sectionPositions["home"] = coordinates.positionInParent().y
                        }
                ) {
                    HeroSection(
                        screenSize = screenSize,
                        onExploreClick = { onNavigate("services") },
                        onContactClick = { onNavigate("contact") }
                    )
                }

                // About Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            sectionPositions["about"] = coordinates.positionInParent().y
                        }
                ) {
                AboutUsSection(screenSize = screenSize)
            }

            // Services Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["services"] = coordinates.positionInParent().y
                    }
            ) {
                ServicesSection(screenSize = screenSize)
            }

            // Tech Stack Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["techstack"] = coordinates.positionInParent().y
                    }
            ) {
                TechStackSection(screenSize = screenSize)
            }

            // Why Rexinnov Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["whyrexinnov"] = coordinates.positionInParent().y
                    }
            ) {
                WhyRexinnovSection(screenSize = screenSize)
            }

            // Products Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["products"] = coordinates.positionInParent().y
                    }
            ) {
                ProductsSection(screenSize = screenSize)
            }


            // Contact Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["contact"] = coordinates.positionInParent().y
                    }
            ) {
                ContactUsSection(screenSize = screenSize)
            }

            // Bottom Navigation / Footer
            BottomNavBar(
                modifier = Modifier.fillMaxWidth(),
                screenSize = screenSize,
                onNavigate = onNavigate
            )
        }

        // Sticky Top Navigation Bar (overlay)
        TopNavBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            screenSize = screenSize,
            onNavigate = onNavigate,
            onCtaClick = { onNavigate("contact") }
        )
    }
}
}
