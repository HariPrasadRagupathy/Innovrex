# Contact Form Email Integration Guide

The contact form is now fully functional with validation and email sending capabilities. Currently, it uses a mock email service that logs the form data to the console. To enable actual email sending, follow one of the integration options below.

## Features Implemented

✅ **Form Validation**
- Full Name: Required, minimum 2 characters
- Email: Required, valid email format
- Subject: Required, minimum 3 characters  
- Message: Required, minimum 10 characters

✅ **Real-time Error Display**
- Field-level validation errors
- Red border on invalid fields
- Error messages below each field

✅ **Form State Management**
- Loading state while submitting
- Success message after submission
- Error message on failure
- Form clears after successful submission
- Disabled inputs during submission

✅ **User Feedback**
- "Sending..." button text during submission
- Loading spinner on button
- Success message (auto-dismisses after 5 seconds)
- Error message (auto-dismisses after 5 seconds)

## Email Integration Options

### Option 1: EmailJS (Recommended for Client-Side)

EmailJS allows you to send emails directly from the client without a backend.

**Steps:**
1. Sign up at [EmailJS](https://www.emailjs.com/)
2. Create an email service (Gmail, Outlook, etc.)
3. Create an email template
4. Get your Public Key, Service ID, and Template ID

**Add Ktor Client dependency** in `build.gradle.kts`:
```kotlin
commonMain.dependencies {
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
}

jsMain.dependencies {
    implementation("io.ktor:ktor-client-js:2.3.7")
}
```

**Update EmailService.kt:**
```kotlin
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.*

suspend fun sendContactEmail(emailData: EmailData): Result<Unit> {
    return withContext(Dispatchers.Default) {
        try {
            val client = HttpClient()
            
            val json = buildJsonObject {
                put("service_id", "YOUR_SERVICE_ID")
                put("template_id", "YOUR_TEMPLATE_ID")
                put("user_id", "YOUR_PUBLIC_KEY")
                putJsonObject("template_params") {
                    put("from_name", emailData.fullName)
                    put("from_email", emailData.email)
                    put("subject", emailData.subject)
                    put("message", emailData.message)
                }
            }
            
            val response = client.post("https://api.emailjs.com/api/v1.0/email/send") {
                contentType(ContentType.Application.Json)
                setBody(json.toString())
            }
            
            client.close()
            
            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to send email: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

### Option 2: Backend API Integration

Send form data to your own backend API endpoint.

**Update EmailService.kt:**
```kotlin
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun sendContactEmail(emailData: EmailData): Result<Unit> {
    return withContext(Dispatchers.Default) {
        try {
            val client = HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
            
            val response = client.post("https://your-api.com/api/contact") {
                contentType(ContentType.Application.Json)
                setBody(emailData)
            }
            
            client.close()
            
            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to send email"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

**Example Backend (Node.js/Express):**
```javascript
const express = require('express');
const nodemailer = require('nodemailer');
const app = express();

app.use(express.json());

app.post('/api/contact', async (req, res) => {
    const { fullName, email, subject, message } = req.body;
    
    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: process.env.EMAIL_USER,
            pass: process.env.EMAIL_PASS
        }
    });
    
    const mailOptions = {
        from: email,
        to: 'contact@rexinnov.com',
        subject: `Contact Form: ${subject}`,
        text: `From: ${fullName} (${email})\n\n${message}`
    };
    
    try {
        await transporter.sendMail(mailOptions);
        res.json({ success: true });
    } catch (error) {
        res.status(500).json({ error: 'Failed to send email' });
    }
});

app.listen(3000);
```

### Option 3: Firebase Functions

Use Firebase Cloud Functions for serverless email sending.

**Setup:**
1. Install Firebase CLI: `npm install -g firebase-tools`
2. Initialize Firebase in your project
3. Deploy the function

**Firebase Function (functions/index.js):**
```javascript
const functions = require('firebase-functions');
const nodemailer = require('nodemailer');

exports.sendContactEmail = functions.https.onCall(async (data, context) => {
    const { fullName, email, subject, message } = data;
    
    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: functions.config().email.user,
            pass: functions.config().email.pass
        }
    });
    
    await transporter.sendMail({
        from: email,
        to: 'contact@rexinnov.com',
        subject: `Contact Form: ${subject}`,
        text: `From: ${fullName} (${email})\n\n${message}`
    });
    
    return { success: true };
});
```

## Testing

To test the form without actual email integration:

1. Fill out all form fields
2. Click "Send Message"
3. Check the browser console for the logged email data
4. The form should show a success message and clear all fields

## Form Fields

- **Full Name**: User's full name
- **Email Address**: User's email for replies
- **Subject**: Brief description of the inquiry
- **Message**: Detailed message content

## Validation Rules

| Field | Required | Min Length | Format |
|-------|----------|------------|--------|
| Full Name | Yes | 2 chars | Any text |
| Email | Yes | - | Valid email format |
| Subject | Yes | 3 chars | Any text |
| Message | Yes | 10 chars | Any text |

## UI States

1. **Default**: Empty form, all fields enabled
2. **Typing**: Real-time error clearing on input
3. **Submitting**: Loading spinner, disabled inputs, "Sending..." button
4. **Success**: Green success message, cleared form
5. **Error**: Red error message, form remains filled for retry

## Next Steps

1. Choose an email integration method above
2. Update the `sendContactEmail` function in `EmailService.kt`
3. Add necessary dependencies to `build.gradle.kts`
4. Configure your email service credentials
5. Test the form submission
6. Deploy your application

## Security Considerations

- Never expose API keys in client-side code
- Use environment variables for sensitive data
- Implement rate limiting to prevent spam
- Add CAPTCHA for production (optional)
- Validate and sanitize all inputs on the backend
- Use HTTPS for all API calls

