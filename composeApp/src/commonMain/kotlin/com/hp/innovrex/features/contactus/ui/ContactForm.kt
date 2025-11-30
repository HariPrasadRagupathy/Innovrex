package com.hp.innovrex.features.contactus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hp.innovrex.designsystem.components.PrimaryButton
import com.hp.innovrex.designsystem.tokens.foundation.BrandColors
import com.hp.innovrex.designsystem.tokens.foundation.SpacingTokens
import com.hp.innovrex.features.contactus.service.EmailData
import com.hp.innovrex.features.contactus.service.sendContactEmail
import innovrex.composeapp.generated.resources.Res
import innovrex.composeapp.generated.resources.correct
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

/**
 * Contact Form Component
 * Form with Full Name, Email, Subject, Message fields
 * Includes validation and email sending functionality
 */
@Composable
fun ContactForm() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // Error states
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var subjectError by remember { mutableStateOf<String?>(null) }
    var messageError by remember { mutableStateOf<String?>(null) }

    // Form state
    var isSubmitting by remember { mutableStateOf(false) }
    var submitSuccess by remember { mutableStateOf(false) }
    var submitError by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    // Validation functions
    fun validateFullName(name: String): String? {
        return when {
            name.isBlank() -> "Full name is required"
            name.length < 2 -> "Name must be at least 2 characters"
            else -> null
        }
    }

    fun validateEmail(email: String): String? {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return when {
            email.isBlank() -> "Email is required"
            !email.matches(emailRegex) -> "Please enter a valid email address"
            else -> null
        }
    }

    fun validateSubject(subject: String): String? {
        return when {
            subject.isBlank() -> "Subject is required"
            subject.length < 3 -> "Subject must be at least 3 characters"
            else -> null
        }
    }

    fun validateMessage(message: String): String? {
        return when {
            message.isBlank() -> "Message is required"
            message.length < 10 -> "Message must be at least 10 characters"
            else -> null
        }
    }

    fun validateForm(): Boolean {
        fullNameError = validateFullName(fullName)
        emailError = validateEmail(email)
        subjectError = validateSubject(subject)
        messageError = validateMessage(message)

        return fullNameError == null && emailError == null &&
               subjectError == null && messageError == null
    }

    // Handle form submission
    fun handleSubmit() {
        if (!validateForm()) return

        coroutineScope.launch {
            isSubmitting = true
            submitError = null
            submitSuccess = false

            try {
                // Send email using the email service
                val emailData = EmailData(
                    fullName = fullName,
                    email = email,
                    subject = subject,
                    message = message
                )

                val result = sendContactEmail(emailData)

                result.onSuccess {
                    submitSuccess = true

                    // Clear form on success
                    fullName = ""
                    email = ""
                    subject = ""
                    message = ""

                    // Reset success message after 5 seconds
                    delay(5000)
                    submitSuccess = false
                }.onFailure {
                    submitError = "Failed to send message. Please try again."
                    delay(5000)
                    submitError = null
                }
            } catch (e: Exception) {
                submitError = "An error occurred. Please try again."
                delay(5000)
                submitError = null
            } finally {
                isSubmitting = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrandColors.White)
            .border(1.dp, BrandColors.Gray200, RoundedCornerShape(16.dp))
            .padding(SpacingTokens.XXL)
    ) {
        // Success message
        if (submitSuccess) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(BrandColors.Green50)
                    .border(1.dp, BrandColors.Green500.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .padding(SpacingTokens.MD)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpacingTokens.SM),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Green checkmark circle
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(BrandColors.Green600),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.correct),
                            contentDescription = "Success",
                            tint = BrandColors.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = "Message sent successfully! We'll get back to you soon.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        ),
                        color = BrandColors.Green700
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpacingTokens.LG))
        }

        // Error message
        if (submitError != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(BrandColors.Red600.copy(alpha = 0.1f))
                    .padding(SpacingTokens.MD)
            ) {
                Text(
                    text = "✗ $submitError",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    color = BrandColors.Red600
                )
            }
            Spacer(modifier = Modifier.height(SpacingTokens.LG))
        }

        // Full Name and Email in Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SpacingTokens.MD)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                FormField(
                    label = "Full Name",
                    value = fullName,
                    onValueChange = {
                        fullName = it
                        fullNameError = null
                    },
                    placeholder = "John Doe",
                    error = fullNameError,
                    enabled = !isSubmitting
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                FormField(
                    label = "Email Address",
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = null
                    },
                    placeholder = "you@example.com",
                    keyboardType = KeyboardType.Email,
                    error = emailError,
                    enabled = !isSubmitting
                )
            }
        }

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        // Subject
        FormField(
            label = "Subject",
            value = subject,
            onValueChange = {
                subject = it
                subjectError = null
            },
            placeholder = "Project Inquiry",
            error = subjectError,
            enabled = !isSubmitting
        )

        Spacer(modifier = Modifier.height(SpacingTokens.LG))

        // Message (Larger text area)
        FormField(
            label = "Message",
            value = message,
            onValueChange = {
                message = it
                messageError = null
            },
            placeholder = "Tell us about your project...",
            minHeight = 120.dp,
            maxLines = 6,
            error = messageError,
            enabled = !isSubmitting
        )

        Spacer(modifier = Modifier.height(SpacingTokens.XL))

        // Send Button
        Box(modifier = Modifier.fillMaxWidth()) {
            PrimaryButton(
                text = if (isSubmitting) "Sending..." else "Send Message",
                onClick = { handleSubmit() },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSubmitting
            )

            if (isSubmitting) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = (-16).dp),
                    color = BrandColors.White,
                    strokeWidth = 2.dp
                )
            }
        }
    }
}

@Composable
private fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    minHeight: Dp = 48.dp,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    error: String? = null,
    enabled: Boolean = true
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
                color = if (enabled) BrandColors.Gray900 else BrandColors.Gray400,
                fontSize = 14.sp
            ),
            maxLines = maxLines,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            cursorBrush = SolidColor(BrandColors.Red600),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = if (error != null) BrandColors.Red600 else BrandColors.Gray300,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(if (enabled) BrandColors.White else BrandColors.Gray100)
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

        // Error message
        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                ),
                color = BrandColors.Red600
            )
        }
    }
}

