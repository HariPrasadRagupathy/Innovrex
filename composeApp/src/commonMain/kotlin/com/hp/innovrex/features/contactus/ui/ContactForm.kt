package com.hp.innovrex.features.contactus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.components.PrimaryButton
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens

/**
 * Contact Form Component
 * Form with Full Name, Email, Subject, Message fields
 */
@Composable
fun ContactForm() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.White)
            .border(1.dp, BrandColors.Gray200, RoundedCornerShape(16.dp))
            .padding(SpacingTokens.XXL)
    ) {
        // Full Name and Email in Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                FormField(
                    label = "Full Name",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "John Doe"
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                FormField(
                    label = "Email Address",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "you@example.com"
                )
            }
        }

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        // Subject
        FormField(
            label = "Subject",
            value = subject,
            onValueChange = { subject = it },
            placeholder = "Project Inquiry"
        )

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        // Message (Larger text area)
        FormField(
            label = "Message",
            value = message,
            onValueChange = { message = it },
            placeholder = "Tell us about your project...",
            minHeight = 120.dp,
            maxLines = 6
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XL))

        // Send Button
        PrimaryButton(
            text = "Send Message",
            onClick = {
                // Handle form submission
                println("Form submitted: $fullName, $email, $subject, $message")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    minHeight: Dp = 48.dp,
    maxLines: Int = 1
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingTokens.XS)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            color = BrandColors.Gray700
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = BrandColors.Gray900,
                fontSize = 14.sp
            ),
            maxLines = maxLines,
            cursorBrush = SolidColor(BrandColors.Red600),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, BrandColors.Gray300, RoundedCornerShape(8.dp))
                        .background(BrandColors.White)
                        .padding(horizontal = SpacingTokens.MD, vertical = SpacingTokens.SM),
                    contentAlignment = if (maxLines == 1) Alignment.CenterStart else Alignment.TopStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp
                            ),
                            color = BrandColors.Gray400
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

