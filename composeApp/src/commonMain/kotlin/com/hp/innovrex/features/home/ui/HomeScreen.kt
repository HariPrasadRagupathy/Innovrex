package com.hp.innovrex.features.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.hp.innovrex.core.nav.ui.TopNavBar
import com.hp.innovrex.designsystem.utils.rememberScreenSize
import com.hp.innovrex.features.home.ui.components.*
import com.hp.innovrex.features.aboutus.ui.AboutUsSection
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
        val navBarHeight = with(density) { 70.dp.toPx() }

        // Handle navigation to sections
        val onNavigate: (String) -> Unit = { sectionId ->
            coroutineScope.launch {
                val position = sectionPositions[sectionId] ?: 0f
                // Offset by navbar height for sections below hero
                val adjustedPosition = if (sectionId == "home") {
                    0
                } else {
                    (position - navBarHeight).coerceAtLeast(0f).toInt()
                }
                scrollState.animateScrollTo(adjustedPosition)
            }
        }

        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Top Navigation Bar
            TopNavBar(
                modifier = Modifier.fillMaxWidth(),
                screenSize = screenSize,
                onNavigate = onNavigate,
                onCtaClick = { onNavigate("contact") }
            )

            // Hero Section (Home)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["home"] = coordinates.positionInRoot().y
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
                        sectionPositions["about"] = coordinates.positionInRoot().y
                    }
            ) {
                AboutUsSection(screenSize = screenSize)
            }

            // Services Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["services"] = coordinates.positionInRoot().y
                    }
            ) {
                ServicesSection(screenSize = screenSize)
            }

            // Products Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["products"] = coordinates.positionInRoot().y
                    }
            ) {
                ProductsSection(screenSize = screenSize)
            }


            // Contact Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        sectionPositions["contact"] = coordinates.positionInRoot().y
                    }
            ) {
                ContactSection(screenSize = screenSize)
            }
        }
    }
}

