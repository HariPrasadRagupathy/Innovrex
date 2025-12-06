package com.hp.innovrex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hp.innovrex.designsystem.theme.InnovrexTheme
import com.hp.innovrex.features.home.ui.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    InnovrexTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            onExploreClick = {
                // Navigate to solutions/products
                println("Explore Solutions clicked")
            },
            onContactClick = {
                // Navigate to contact section
                println("Contact Us clicked")
            }
        )
    }
}