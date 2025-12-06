# 🎯 Quick Configuration Guide

## Where to Add Your EmailJS Credentials

### File Location:
```
/Volumes/files/innovrex/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/contactus/service/EmailService.kt
```

### Lines to Update (around line 24-28):

**BEFORE (Default - Won't Work):**
```kotlin
private object EmailJSConfig {
    const val SERVICE_ID = "YOUR_SERVICE_ID"  // ⚠️ Replace this
    const val TEMPLATE_ID = "YOUR_TEMPLATE_ID"  // ⚠️ Replace this
    const val PUBLIC_KEY = "YOUR_PUBLIC_KEY"  // ⚠️ Replace this
}
```

**AFTER (Example - Use Your Actual Values):**
```kotlin
private object EmailJSConfig {
    const val SERVICE_ID = "service_abc1234"  // ✅ From EmailJS dashboard
    const val TEMPLATE_ID = "template_xyz5678"  // ✅ From EmailJS template
    const val PUBLIC_KEY = "user_def9012hij"  // ✅ From EmailJS account settings
}
```

---

## Where to Find Each Credential

### 1. SERVICE_ID 🔧
1. Go to [EmailJS Dashboard](https://dashboard.emailjs.com/)
2. Click **"Email Services"** (left sidebar)
3. Find your connected service
4. Copy the **Service ID** (e.g., `service_abc1234`)

**Example:**
```
Gmail Service
Service ID: service_abc1234  ← Copy this
```

### 2. TEMPLATE_ID 📧
1. In EmailJS Dashboard, click **"Email Templates"**
2. Find or create your template
3. Copy the **Template ID** (e.g., `template_xyz5678`)

**Example:**
```
Contact Form Template
Template ID: template_xyz5678  ← Copy this
```

### 3. PUBLIC_KEY 🔑
1. Click your **account name** (top right)
2. Go to **"Account"** → **"General"**
3. Find **"Public Key"** section
4. Copy the key (e.g., `user_def9012hij`)

**Example:**
```
Account Settings > General
Public Key: user_def9012hij  ← Copy this
```

---

## Visual Example

```
┌─────────────────────────────────────────────┐
│  EmailJS Dashboard                          │
├─────────────────────────────────────────────┤
│                                             │
│  📧 Email Services                          │
│     └─ Gmail Service                        │
│        ID: service_abc1234  ← Copy this     │
│                                             │
│  📝 Email Templates                         │
│     └─ Contact Form                         │
│        ID: template_xyz5678 ← Copy this     │
│                                             │
│  👤 Account > General                       │
│     └─ Public Key                           │
│        Key: user_def9012hij ← Copy this     │
│                                             │
└─────────────────────────────────────────────┘
```

---

## After Updating

### 1. Your EmailService.kt should look like:
```kotlin
private object EmailJSConfig {
    const val SERVICE_ID = "service_abc1234"      // ✅ Your actual Service ID
    const val TEMPLATE_ID = "template_xyz5678"    // ✅ Your actual Template ID
    const val PUBLIC_KEY = "user_def9012hij"      // ✅ Your actual Public Key
}
```

### 2. Rebuild the project:
```bash
./gradlew clean build
```

### 3. Run the application:
```bash
./gradlew jsBrowserRun
```

### 4. Test the form:
- Fill out all fields
- Click "Send Message"
- Check your email inbox!

---

## Email Template Setup

### Recommended Template Structure:

**Subject Line:**
```
New Contact: {{subject}}
```

**Email Body (HTML):**
```html
<h2>New Contact Form Submission</h2>

<p><strong>Name:</strong> {{from_name}}</p>
<p><strong>Email:</strong> {{from_email}}</p>
<p><strong>Subject:</strong> {{subject}}</p>

<h3>Message:</h3>
<p>{{message}}</p>

<hr>
<small>Sent from Rexinnov Contact Form</small>
```

**To Email:**
```
contact@rexinnov.com
```
(Or use `{{to_email}}` variable)

### Template Variables to Use:
- `{{from_name}}` - User's full name
- `{{from_email}}` - User's email address  
- `{{subject}}` - Message subject
- `{{message}}` - Message content
- `{{to_email}}` - Your receiving email

---

## Verification Checklist

Before testing:
- [ ] Copied Service ID from EmailJS
- [ ] Copied Template ID from EmailJS
- [ ] Copied Public Key from EmailJS
- [ ] Replaced all three values in `EmailJSConfig`
- [ ] Saved the file
- [ ] No quotes or typos in IDs
- [ ] Ran `./gradlew clean build`

After testing:
- [ ] Form submits without errors
- [ ] "Sending..." appears during submission
- [ ] Success message appears
- [ ] Form fields clear automatically
- [ ] Email arrives in inbox
- [ ] All form data is in the email

---

## Common Mistakes ❌ → ✅

### ❌ Wrong: Keeping placeholder text
```kotlin
const val SERVICE_ID = "YOUR_SERVICE_ID"  // Won't work!
```

### ✅ Correct: Using actual ID
```kotlin
const val SERVICE_ID = "service_abc1234"  // Works!
```

---

### ❌ Wrong: Adding extra quotes
```kotlin
const val SERVICE_ID = ""service_abc1234""  // Syntax error!
```

### ✅ Correct: Single quotes only
```kotlin
const val SERVICE_ID = "service_abc1234"  // Works!
```

---

### ❌ Wrong: Typo in ID
```kotlin
const val SERVICE_ID = "servic_abc1234"  // Missing 'e'!
```

### ✅ Correct: Exact copy from EmailJS
```kotlin
const val SERVICE_ID = "service_abc1234"  // Works!
```

---

## Need Help?

### Can't find credentials?
- **Service ID:** EmailJS Dashboard → Email Services → Select service
- **Template ID:** EmailJS Dashboard → Email Templates → Select template
- **Public Key:** EmailJS Dashboard → Account icon (top right) → General

### Still not working?
1. Check browser console for errors
2. Verify EmailJS service is active (green indicator)
3. Test template directly in EmailJS dashboard
4. Check that all template variables match
5. Ensure internet connection is working

### Getting CORS errors?
- Make sure you're using the **Public Key** (not Private Key)
- Verify your domain is allowed in EmailJS settings

---

## Summary

**3 Simple Steps:**

1. **Get credentials** from EmailJS dashboard
2. **Replace** placeholder values in `EmailService.kt`
3. **Rebuild** and test!

That's it! Your contact form will now send real emails. 🎉

---

**Quick Links:**
- [EmailJS Dashboard](https://dashboard.emailjs.com/)
- [EmailJS Documentation](https://www.emailjs.com/docs/)
- [Detailed Setup Guide](/docs/EMAILJS_SETUP_GUIDE.md)

