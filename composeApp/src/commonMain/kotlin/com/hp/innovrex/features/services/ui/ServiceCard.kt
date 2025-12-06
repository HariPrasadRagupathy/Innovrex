package com.hp.innovrex.features.services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.designsystem.utils.ScreenSize
import innovrex.composeapp.generated.resources.Res
import innovrex.composeapp.generated.resources.correct
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Reusable Service Card Component
 * Displays a service with image, title, description, and features
 */
@Composable
fun ServiceCard(
    title: String,
    description: String,
    features: List<ServiceFeature>,
    image: DrawableResource,
    screenSize: ScreenSize,
    imageOnLeft: Boolean = true,
    modifier: Modifier = Modifier
) {
    val isMobile = screenSize == ScreenSize.Mobile

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.White)
            .padding(SpacingTokens.XXL)
    ) {
        if (isMobile) {
            // Mobile: Stacked layout
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(SpacingTokens.XL)
            ) {
                ServiceImage(image)
                ServiceContent(title, description, features)
            }
        } else {
            // Desktop: Side-by-side layout
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SpacingTokens.Huge),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (imageOnLeft) {
                    Box(modifier = Modifier.weight(1f)) {
                        ServiceImage(image)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        ServiceContent(title, description, features)
                    }
                } else {
                    Box(modifier = Modifier.weight(1f)) {
                        ServiceContent(title, description, features)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        ServiceImage(image)
                    }
                }
            }
        }
    }
}

@Composable
private fun ServiceImage(image: DrawableResource, contentDescription : String = "Service image") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.2f)
            .clip(RoundedCornerShape(12.dp))
            .background(BrandColors.Gray100),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Service image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun ServiceContent(
    title: String,
    description: String,
    features: List<ServiceFeature>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.LG)
    ) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            color = BrandColors.Gray900
        )

        // Description
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = BrandColors.Gray600
        )

        Spacer(modifier = Modifier.height(SpacingTokens.SM))

        // Features list
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.LG)
        ) {
            features.forEach { feature ->
                FeatureItem(feature)
            }
        }
    }
}

@Composable
private fun FeatureItem(feature: ServiceFeature) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
        verticalAlignment = Alignment.Top
    ) {
        // Icon indicator
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(BrandColors.Red600.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(feature.icon ?: Res.drawable.correct),
                contentDescription = feature.title,
                modifier = Modifier.size(20.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Content
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XS)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = BrandColors.Gray900
            )
            Text(
                text = feature.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                ),
                color = BrandColors.Gray600
            )
        }
    }
}

