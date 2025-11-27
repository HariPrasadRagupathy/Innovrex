package com.hp.innovrex.features.services.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize

/**
 * Cross-Platform App Development Service Card
 */
@Composable
fun CrossPlatformAppDevelopmentCard(screenSize: ScreenSize) {
    ServiceCard(
        title = "Cross-Platform App Development",
        description = "We build applications with a single, shared codebase that delivers a native-like user experience and feel across all platforms, reducing costs and the time to market.",
        features = listOf(
            ServiceFeature("Mobile Apps", "Build feature-rich and robust mobile apps for both Android and iOS with Kotlin Multiplatform"),
            ServiceFeature("Desktop Apps", "Create powerful desktop applications that work beautifully on Windows, macOS, and Linux"),
            ServiceFeature("Web Applications", "Develop responsive and modern web applications sharing logic with mobile and desktop apps")
        ),
        imagePlaceholder = "Devices Illustration",
        screenSize = screenSize,
        imageOnLeft = true
    )
}

/**
 * Custom Software Solutions Service Card
 */
@Composable
fun CustomSoftwareSolutionsCard(screenSize: ScreenSize) {
    ServiceCard(
        title = "Custom Software Solutions",
        description = "From idea to final delivery, we develop customized, scalable software solutions with Kotlin Multiplatform that perfectly align with your unique business needs and goals.",
        features = listOf(
            ServiceFeature("Enterprise-Grade Applications", "Build robust and secure applications designed for enterprise needs, with scalability, maintainability, and performance at the core"),
            ServiceFeature("API & Backend Integration", "Seamlessly integrate your multiplatform apps with backend services, third-party APIs, using Ktor and Kotlin for unified tech stack"),
            ServiceFeature("Secure Data Handling", "Ensure your users' data is protected with best-in-class encryption and secure coding standards")
        ),
        imagePlaceholder = "Security Illustration",
        screenSize = screenSize,
        imageOnLeft = false
    )
}

/**
 * Consulting & Architecture Service Card
 */
@Composable
fun ConsultingArchitectureCard(screenSize: ScreenSize) {
    ServiceCard(
        title = "Consulting & Architecture",
        description = "Our expert-grade Kotlin Multiplatform architects and consultants can help you plan, design, and execute your cross-platform strategy, accelerating time-to-market and ensuring long-term success.",
        features = listOf(
            ServiceFeature("Application Architecture Setup", "Design robust and scalable application architectures following best practices for Kotlin Multiplatform projects"),
            ServiceFeature("Performance & Scalability", "Optimize your applications for superior performance and ensure they scale efficiently as your user base grows"),
            ServiceFeature("Migration to KMP", "Migrate your existing native iOS and Android apps to a unified Kotlin Multiplatform architecture")
        ),
        imagePlaceholder = "Consulting Illustration",
        screenSize = screenSize,
        imageOnLeft = true
    )
}

/**
 * Training & Workshops Service Card
 */
@Composable
fun TrainingWorkshopsCard(screenSize: ScreenSize) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.White)
            .padding(SpacingTokens.XXL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Training & Workshops",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            color = BrandColors.Gray900
        )

        Spacer(modifier = Modifier.height(SpacingTokens.MD))

        Text(
            text = "Empower your team with the knowledge and skills to build in Kotlin Multiplatform applications through our hands-on, expert-led training sessions.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray600
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XXL))

        // Training features in grid
        val isMobile = screenSize == ScreenSize.Mobile

        if (isMobile) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
            ) {
                TrainingFeatureItem("Kotlin Multiplatform fundamentals")
                TrainingFeatureItem("Building shared modules and libraries")
                TrainingFeatureItem("Compose Multiplatform UI development")
                TrainingFeatureItem("Integration with existing codebases")
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
                    modifier = Modifier.weight(1f)
                ) {
                    TrainingFeatureItem("Kotlin Multiplatform fundamentals")
                    TrainingFeatureItem("Compose Multiplatform UI development")
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
                    modifier = Modifier.weight(1f)
                ) {
                    TrainingFeatureItem("Building shared modules and libraries")
                    TrainingFeatureItem("Integration with existing codebases")
                }
            }
        }
    }
}

@Composable
private fun TrainingFeatureItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.SM)
    ) {
        Text(
            text = "✓",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = BrandColors.Red600,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp
            ),
            color = BrandColors.Gray700
        )
    }
}

/**
 * Data class for service features
 */
data class ServiceFeature(
    val title: String,
    val description: String
)

