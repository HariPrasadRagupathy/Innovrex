package com.hp.innovrex.features.contactus.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import innovrex.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Contact Information Card
 * Displays email, phone, location, and network image
 */
@Composable
fun ContactInformationCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.White)
            .border(1.dp, BrandColors.Gray200, RoundedCornerShape(16.dp))
            .padding(SpacingTokens.XXL)
    ) {
        Text(
            text = "Contact Information",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = BrandColors.Gray900
        )

        Spacer(modifier = Modifier.height(SpacingTokens.MD))

        Text(
            text = "Reach out to us through any of the following channels. We're excited to discuss how we can help you achieve your goals.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            color = BrandColors.Gray600
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XL))

        // Contact Details
        ContactDetail(
            icon = Res.drawable.email_icon,
            label = "Email Us",
            value = "contact@rexinnov.com"
        )

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        ContactDetail(
            icon = Res.drawable.phone_icon,
            label = "Call Us",
            value = "+1 (555) 123-4567"
        )

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        ContactDetail(
            icon = Res.drawable.location_icon,
            label = "Our Location",
            value = "Global Services"
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XL))

        // Network Image Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.4f)
                .clip(RoundedCornerShape(12.dp))
                .background(BrandColors.DarkBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🌐 Global Network",
                style = MaterialTheme.typography.titleMedium,
                color = BrandColors.White
            )
        }
    }
}

@Composable
private fun ContactDetail(
    icon: DrawableResource,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD),
        verticalAlignment = Alignment.Top
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(BrandColors.Red600.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier
                    .size(18.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Label and Value
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(SpacingTokens.XXS)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
                color = BrandColors.Gray900
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                color = BrandColors.Gray600
            )
        }
    }
}

