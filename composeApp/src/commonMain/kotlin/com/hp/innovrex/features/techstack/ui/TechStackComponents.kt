package com.hp.innovrex.features.techstack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

/**
 * Technology List Component
 * Displays categories and technologies in a table format
 */
@Composable
fun TechnologyList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BrandColors.Gray200, RoundedCornerShape(12.dp))
            .background(BrandColors.White)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandColors.Gray50)
                .padding(horizontal = SpacingTokens.LG, vertical = SpacingTokens.MD)
        ) {
            Text(
                text = "Category",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
                color = BrandColors.Gray700,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Technology",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
                color = BrandColors.Gray700,
                modifier = Modifier.weight(1f)
            )
        }

        // Technology Rows
        TechRow("Core Language", "Kotlin")
        TechRow("Development Approach", "Kotlin Multiplatform")
        TechRow("Cross-Platform Framework", "Compose Multiplatform")
        TechRow("Backend", "Ktor")
        TechRow("Databases", "SQL Delight, Realm, Room")
        TechRow("Dependency Injection", "Koin, Dagger")
        TechRow("Data Layer", "Ktor Client / OkHttp / Core API")
        TechRow("Serialization", "kotlinx.serialization")
    }
}

@Composable
private fun TechRow(category: String, technology: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandColors.White)
            .padding(horizontal = SpacingTokens.LG, vertical = SpacingTokens.MD)
            .border(
                width = 0.5.dp,
                color = BrandColors.Gray200,
                shape = RoundedCornerShape(0.dp)
            )
            .padding(horizontal = SpacingTokens.SM, vertical = SpacingTokens.SM)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            color = BrandColors.Gray900,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = technology,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            color = BrandColors.Gray700,
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Supported Platforms Component
 * Displays platform logos in a grid
 */
@Composable
fun SupportedPlatforms() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BrandColors.Gray200, RoundedCornerShape(12.dp))
            .background(BrandColors.White)
            .padding(SpacingTokens.XXL)
    ) {
        Text(
            text = "Supported Platforms",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = BrandColors.Gray900
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XL))

        // Platform Grid
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.LG)
        ) {
            // Row 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SpacingTokens.LG)
            ) {
                PlatformCard("Android", modifier = Modifier.weight(1f))
                PlatformCard("iOS", modifier = Modifier.weight(1f))
            }

            // Row 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SpacingTokens.LG)
            ) {
                PlatformCard("Windows", modifier = Modifier.weight(1f))
                PlatformCard("Web", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun PlatformCard(platformName: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(2.5f)
            .clip(RoundedCornerShape(8.dp))
            .background(BrandColors.Gray50)
            .border(1.dp, BrandColors.Gray200, RoundedCornerShape(8.dp))
            .padding(SpacingTokens.MD),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.SM)
        ) {
            // Platform icon placeholder
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(BrandColors.Gray300)
            )

            Text(
                text = platformName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                color = BrandColors.Gray900
            )
        }
    }
}

