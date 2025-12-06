package com.hp.innovrex.features.whyrexinnov.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import innovrex.composeapp.generated.resources.Res
import innovrex.composeapp.generated.resources.codeIcon
import innovrex.composeapp.generated.resources.correct
import org.jetbrains.compose.resources.painterResource

/**
 * Codebase Advantages Card
 */
@Composable
fun CodebaseAdvantagesCard() {
    AdvantageCard(
        icon = "🚀",
        title = "Codebase Advantages",
        advantages = listOf(
            "One shared Kotlin codebase for all platforms",
            "Reduced development and maintenance cost",
            "Native-like performance and UI consistency",
            "Faster time-to-market for new features"
        )
    )
}

/**
 * Expert Team Support Card
 */
@Composable
fun ExpertTeamSupportCard() {
    AdvantageCard(
        icon = "👥",
        title = "Expert Team Support",
        advantages = listOf(
            "Deep experience in KMP and Jetpack Compose",
            "Training and support for your in-house teams",
            "Ongoing maintenance and optimization",
            "Dedicated project management"
        )
    )
}

/**
 * Reusable Advantage Card Component
 */
@Composable
private fun AdvantageCard(
    icon: String,
    title: String,
    advantages: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.DarkSurface)
            .border(1.dp, BrandColors.Gray700, RoundedCornerShape(16.dp))
            .padding(SpacingTokens.XXL)
    ) {
        // Icon and Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
        ) {
            // Icon
            Image(
                painter = painterResource(Res.drawable.codeIcon),
                contentDescription = "Team collaboration image",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(50)),
                contentScale = ContentScale.Fit
            )

            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = BrandColors.White
            )
        }

        Spacer(modifier = Modifier.height(SpacingTokens.XL))

        // Advantages List
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
        ) {
            advantages.forEach { advantage ->
                AdvantageItem(advantage)
            }
        }
    }
}

/**
 * Individual Advantage Item with Checkmark
 */
@Composable
private fun AdvantageItem(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
        verticalAlignment = Alignment.Top
    ) {
        // Checkmark
        Image(
            painter = painterResource(Res.drawable.correct),
            contentDescription = "Checkmark",
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Fit
        )

        // Text
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp,
                lineHeight = 22.sp
            ),
            color = BrandColors.Gray300,
            modifier = Modifier.weight(1f)
        )
    }
}

