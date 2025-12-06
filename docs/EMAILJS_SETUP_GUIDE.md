# EmailJS Setup Guide

This guide will walk you through setting up EmailJS for your contact form.

## Step 1: Create EmailJS Account

1. Go to [EmailJS.com](https://www.emailjs.com/)
2. Click "Sign Up" and create a free account
3. Verify your email address

## Step 2: Add Email Service

1. In the EmailJS dashboard, click "Email Services"
2. Click "Add New Service"
3. Choose your email provider (Gmail, Outlook, etc.)
4. Follow the prompts to connect your email account
5. Copy your **Service ID** (e.g., `service_abc123`)

### Recommended: Gmail Setup

If using Gmail:
1. Select "Gmail" as your service
2. Click "Connect Account"
3. Allow EmailJS to access your Gmail
4. Your Service ID will be generated automatically

## Step 3: Create Email Template

1. In the EmailJS dashboard, click "Email Templates"
2. Click "Create New Template"
3. Use this template structure:

### Template Content:

**Subject:**
```
New Contact Form Submission: {{subject}}
```

**Body (HTML):**
```html
<h2>New Contact Form Submission</h2>

<p><strong>From:</strong> {{from_name}}</p>
<p><strong>Email:</strong> {{from_email}}</p>
<p><strong>Subject:</strong> {{subject}}</p>

<h3>Message:</h3>
<p>{{message}}</p>

<hr>
<p><small>This message was sent from the Rexinnov contact form.</small></p>
```

**To Email:**
```
{{to_email}}
```

4. Save the template and copy your **Template ID** (e.g., `template_xyz789`)

### Template Variables

The contact form sends these variables:
- `from_name` - User's full name
- `from_email` - User's email address
- `subject` - Message subject
- `message` - Message content
- `to_email` - Your receiving email (contact@rexinnov.com)

## Step 4: Get Your Public Key

1. Click on your account name (top right)
2. Go to "Account" → "General"
3. Find your **Public Key** (e.g., `abcd1234efgh5678`)

## Step 5: Update EmailService.kt

Open `/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/contactus/service/EmailService.kt`

Replace these values in the `EmailJSConfig` object:

```kotlin
private object EmailJSConfig {
    const val SERVICE_ID = "service_abc123"      // Your Service ID
    const val TEMPLATE_ID = "template_xyz789"    // Your Template ID
    const val PUBLIC_KEY = "abcd1234efgh5678"    // Your Public Key
}
```

### Example:

```kotlin
private object EmailJSConfig {
    const val SERVICE_ID = "service_rxn4k8s"
    const val TEMPLATE_ID = "template_contact_form"
    const val PUBLIC_KEY = "user_rX9nK8sP0qL2mN3v"
}
```

## Step 6: Test the Form

1. Rebuild your project:
   ```bash
   ./gradlew clean build
   ```

2. Run your application:
   ```bash
   ./gradlew jsBrowserRun
   ```

3. Navigate to the contact form
4. Fill out all fields with test data
5. Click "Send Message"
6. Check your email inbox for the test message

## Step 7: Verify Email Delivery

### Check EmailJS Dashboard:
1. Go to EmailJS dashboard
2. Click "Email Logs"
3. You should see your test email listed
4. Status should be "Sent"

### Check Your Inbox:
1. Open your email (the one you set as recipient)
2. Look for the email from your connected service
3. Verify all form data is present

## Troubleshooting

### Issue: Email not sending

**Check:**
- [ ] All credentials are correct in `EmailJSConfig`
- [ ] No typos in Service ID, Template ID, or Public Key
- [ ] EmailJS service is connected and active
- [ ] Template variables match the ones being sent
- [ ] Internet connection is working
- [ ] Browser console for error messages

### Issue: CORS errors

EmailJS should handle CORS automatically, but if you see CORS errors:
1. Verify your domain is allowed in EmailJS settings
2. Make sure you're using the Public Key (not Private Key)
3. Check EmailJS dashboard for any restrictions

### Issue: Rate limiting

EmailJS free tier limits:
- 200 emails per month
- Rate limit protection against spam

**Solutions:**
- Upgrade to paid plan for more emails
- Implement client-side rate limiting
- Add CAPTCHA to prevent spam

### Issue: Template variables not showing

**Check:**
- [ ] Variable names in template match exactly (case-sensitive)
- [ ] Variables use double curly braces: `{{variable_name}}`
- [ ] All required variables are being sent from the form

## Security Best Practices

### ✅ DO:
- Use environment variables for production
- Enable EmailJS rate limiting
- Add CAPTCHA for production sites
- Monitor EmailJS logs regularly
- Use HTTPS for your website

### ❌ DON'T:
- Expose your Private Key
- Commit credentials to version control
- Allow unlimited form submissions
- Skip input validation
- Use http:// in production

## Production Deployment

### Option 1: Environment Variables (Recommended)

Create a config file (don't commit):
```kotlin
// EmailConfig.kt (add to .gitignore)
object EmailJSConfig {
    const val SERVICE_ID = System.getenv("EMAILJS_SERVICE_ID")
    const val TEMPLATE_ID = System.getenv("EMAILJS_TEMPLATE_ID")
    const val PUBLIC_KEY = System.getenv("EMAILJS_PUBLIC_KEY")
}
```

### Option 2: Build Configuration

Use Gradle build variants to inject credentials at build time.

### Option 3: Backend Proxy (Most Secure)

Instead of calling EmailJS directly from the frontend:
1. Create a backend endpoint
2. Store EmailJS credentials on server
3. Frontend calls your backend
4. Backend calls EmailJS

## Email Template Customization

### Professional Template Example:

```html
<!DOCTYPE html>
<html>
<head>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
        .header { background: #DC2626; color: white; padding: 20px; text-align: center; }
        .content { background: #f5f5f5; padding: 20px; }
        .field { margin-bottom: 15px; }
        .label { font-weight: bold; color: #333; }
        .value { color: #666; margin-top: 5px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>New Contact Form Submission</h1>
        </div>
        <div class="content">
            <div class="field">
                <div class="label">From:</div>
                <div class="value">{{from_name}}</div>
            </div>
            <div class="field">
                <div class="label">Email:</div>
                <div class="value">{{from_email}}</div>
            </div>
            <div class="field">
                <div class="label">Subject:</div>
                <div class="value">{{subject}}</div>
            </div>
            <div class="field">
                <div class="label">Message:</div>
                <div class="value">{{message}}</div>
            </div>
        </div>
    </div>
</body>
</html>
```

## Auto-Reply Template (Optional)

Create a second template to send automatic replies to users:

**Template Name:** `template_auto_reply`

**Subject:**
```
Thank you for contacting Rexinnov
```

**Body:**
```html
<p>Hi {{from_name}},</p>

<p>Thank you for reaching out to us! We have received your message and will get back to you as soon as possible.</p>

<p><strong>Your message:</strong></p>
<p>{{message}}</p>

<p>Best regards,<br>
The Rexinnov Team</p>
```

## Support

- EmailJS Documentation: https://www.emailjs.com/docs/
- EmailJS Support: support@emailjs.com
- Rexinnov Contact Form Docs: `/docs/CONTACT_FORM_IMPLEMENTATION.md`

## Quick Reference

### EmailJS API Endpoint:
```
POST https://api.emailjs.com/api/v1.0/email/send
```

### Request Body Structure:
```json
{
  "service_id": "YOUR_SERVICE_ID",
  "template_id": "YOUR_TEMPLATE_ID",
  "user_id": "YOUR_PUBLIC_KEY",
  "template_params": {
    "from_name": "John Doe",
    "from_email": "john@example.com",
    "subject": "Project Inquiry",
    "message": "Hello, I'd like to discuss...",
    "to_email": "contact@rexinnov.com"
  }
}
```

### Success Response:
```
Status: 200 OK
Body: "OK"
```

### Error Response:
```
Status: 4xx/5xx
Body: Error message
```

---

**Need Help?** Check the troubleshooting section or contact EmailJS support.

