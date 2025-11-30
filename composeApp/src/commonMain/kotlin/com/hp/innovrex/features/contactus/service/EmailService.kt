package com.hp.innovrex.features.contactus.service

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

/**
 * Email Service for sending contact form submissions via EmailJS
 */

/**
 * Data class for email content
 */
@Serializable
data class EmailData(
    val fullName: String,
    val email: String,
    val subject: String,
    val message: String
)

/**
 * EmailJS Configuration
 * Replace these with your actual EmailJS credentials
 */
private object EmailJSConfig {
    const val SERVICE_ID = "service_9dcxbjn"  // Replace with your EmailJS Service ID
    const val TEMPLATE_ID = "template_wwrvwq9"  // Replace with your EmailJS Template ID
    const val PUBLIC_KEY = "gzXsWJdlnT8G5go2j"  // Replace with your EmailJS Public Key
}

/**
 * Send email using EmailJS service
 *
 * @param emailData The email content to send
 * @return Result indicating success or failure
 */
suspend fun sendContactEmail(emailData: EmailData): Result<Unit> {
    return withContext(Dispatchers.Default) {
        try {
            val client = HttpClient()

            // Build the JSON payload for EmailJS
            val jsonPayload = buildJsonObject {
                put("service_id", EmailJSConfig.SERVICE_ID)
                put("template_id", EmailJSConfig.TEMPLATE_ID)
                put("user_id", EmailJSConfig.PUBLIC_KEY)
                putJsonObject("template_params") {
                    put("from_name", emailData.fullName)
                    put("from_email", emailData.email)
                    put("subject", emailData.subject)
                    put("message", emailData.message)
                    put("to_email", "rexinnov@gmail.com") // Your receiving email
                }
            }

            // Debug: Print the payload (remove in production)
            println("📧 Sending email via EmailJS...")
            println("Service ID: ${EmailJSConfig.SERVICE_ID}")
            println("Template ID: ${EmailJSConfig.TEMPLATE_ID}")
            println("Public Key: ${EmailJSConfig.PUBLIC_KEY}")

            // Send POST request to EmailJS API
            val response = client.post("https://api.emailjs.com/api/v1.0/email/send") {
                contentType(ContentType.Application.Json)
                setBody(jsonPayload.toString())
            }

            val responseBody = response.bodyAsText()

            client.close()

            // Check response status
            if (response.status.isSuccess()) {
                println("✓ Email sent successfully via EmailJS")
                println("Response: $responseBody")
                Result.success(Unit)
            } else {
                val errorMessage = "Failed to send email: ${response.status}"
                println("✗ $errorMessage")
                println("Response Body: $responseBody")
                println("\n🔍 Troubleshooting:")
                println("1. Verify your Public Key in EmailJS dashboard (Account > General)")
                println("2. Check that Service ID '${ EmailJSConfig.SERVICE_ID}' is active")
                println("3. Check that Template ID '${EmailJSConfig.TEMPLATE_ID}' exists")
                println("4. Ensure no domain restrictions in EmailJS settings")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            println("✗ Email sending error: ${e.message}")
            println("Stack trace: ${e.stackTraceToString()}")
            Result.failure(e)
        }
    }
}

