package com.hp.innovrex.features.home.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.hp.innovrex.designsystem.utils.rememberScreenSize
import com.hp.innovrex.features.home.ui.components.HeroSection

/**
 * Home screen with responsive layout
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

        // Scrollable content
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection(
                screenSize = screenSize,
                onExploreClick = onExploreClick,
                onContactClick = onContactClick
            )

            // Add more sections here as needed
            // FeaturesSection()
            // ServicesSection()
            // ContactSection()
        }
    }
}

