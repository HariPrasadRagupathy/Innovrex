# EmailJS 403 Forbidden Error - Troubleshooting Guide

## Error You're Seeing

```
✗ Failed to send email: 403 Forbidden
```

This error means EmailJS is **rejecting your request**. Here's how to fix it:

---

## Common Causes & Solutions

### 1. ❌ Wrong Public Key (Most Common)

**Problem:** The Public Key in your code doesn't match EmailJS dashboard.

**Solution:**

1. Go to [EmailJS Dashboard](https://dashboard.emailjs.com/)
2. Click your **account name** (top right corner)
3. Select **"Account"** → **"General"**
4. Find **"Public Key"** section
5. Copy the EXACT key shown there

**Important Notes:**
- The key might be called "User ID" or "Public Key"
- It should look like: `user_xxxxxxxxx` or just `xxxxxxxxx`
- Copy it exactly - no extra spaces!

**Update your EmailService.kt:**
```kotlin
private object EmailJSConfig {
    const val SERVICE_ID = "service_9dcxbjn"
    const val TEMPLATE_ID = "template_wwrvwq9"
    const val PUBLIC_KEY = "YOUR_EXACT_PUBLIC_KEY_HERE"  // ← Replace this!
}
```

---

### 2. ❌ Service Not Active or Wrong Service ID

**Problem:** Your email service is inactive or the ID is wrong.

**Solution:**

1. Go to [EmailJS Dashboard](https://dashboard.emailjs.com/)
2. Click **"Email Services"** (left sidebar)
3. You should see your connected service (Gmail, etc.)
4. Check:
   - ✅ Green indicator = Active
   - ❌ Red indicator = Inactive (click to activate)
5. Copy the **Service ID** exactly

**Verify:**
```kotlin
const val SERVICE_ID = "service_9dcxbjn"  // Must match dashboard exactly
```

---

### 3. ❌ Template Doesn't Exist or Wrong Template ID

**Problem:** Template ID is incorrect or template was deleted.

**Solution:**

1. Go to **"Email Templates"** in EmailJS dashboard
2. Find your contact form template
3. Copy the **Template ID** exactly
4. Make sure template status is **Active** (not draft)

**Verify:**
```kotlin
const val TEMPLATE_ID = "template_wwrvwq9"  // Must match exactly
```

---

### 4. ❌ Domain Restrictions Enabled

**Problem:** EmailJS is blocking requests from localhost or your domain.

**Solution:**

1. In EmailJS dashboard, go to **"Account"** → **"Security"**
2. Check **"Allowed Origins"** section
3. Options:
   - **Option A (Development):** Remove all domain restrictions
   - **Option B (Specific):** Add your domain:
     - For desktop: `http://localhost`
     - For web dev: `http://localhost:8080`
     - For production: `https://yourdomain.com`

---

### 5. ❌ Account Limitations (Free Tier)

**Problem:** You've exceeded the free tier limits.

**Check:**
1. EmailJS Dashboard → **"Quota"**
2. Free tier: 200 emails/month
3. If exceeded, upgrade or wait for reset

---

## Step-by-Step Fix (Do This Now)

### Step 1: Verify Your Public Key

1. Open [EmailJS Dashboard](https://dashboard.emailjs.com/)
2. Click your profile icon (top right)
3. Go to **Account** → **General**
4. Find **"Public Key"**
5. **Copy it exactly**

**It should look like ONE of these formats:**
- `user_abc123def456`
- `abc123def456ghi789`
- Just a string of random characters

### Step 2: Update EmailService.kt

Open: `/Volumes/files/innovrex/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/contactus/service/EmailService.kt`

Find line ~32 and replace:
```kotlin
const val PUBLIC_KEY = "gzXsWJdlnT8G5go2j"  // ← Replace with YOUR key from Step 1
```

### Step 3: Verify Service ID

1. EmailJS Dashboard → **Email Services**
2. Click on your service (Gmail, etc.)
3. Copy the **Service ID** shown at top
4. It should look like: `service_xxxxxxx`

**Update if different:**
```kotlin
const val SERVICE_ID = "service_9dcxbjn"  // Must match your dashboard
```

### Step 4: Verify Template ID

1. EmailJS Dashboard → **Email Templates**
2. Click on your contact form template
3. Copy the **Template ID** shown at top
4. It should look like: `template_xxxxxxx`

**Update if different:**
```kotlin
const val TEMPLATE_ID = "template_wwrvwq9"  // Must match your dashboard
```

### Step 5: Check Template Variables

Your template MUST have these variables defined:

Required in template:
- `{{from_name}}`
- `{{from_email}}`
- `{{subject}}`
- `{{message}}`
- `{{to_email}}` (optional, can be hardcoded)

**Template Example:**
```
Subject: New Contact: {{subject}}

From: {{from_name}} ({{from_email}})

Message:
{{message}}
```

### Step 6: Test Again

1. Rebuild your app:
   ```bash
   ./gradlew clean
   ./gradlew :composeApp:run
   ```

2. Fill out the contact form
3. Click "Send Message"
4. Check console output for detailed error info

---

## Console Debugging

With the updated code, you'll now see:

**If credentials are wrong:**
```
📧 Sending email via EmailJS...
Service ID: service_9dcxbjn
Template ID: template_wwrvwq9
Public Key: gzXsWJdlnT8G5go2j
✗ Failed to send email: 403 Forbidden
Response Body: {"error":"Invalid public key"}

🔍 Troubleshooting:
1. Verify your Public Key in EmailJS dashboard (Account > General)
2. Check that Service ID 'service_9dcxbjn' is active
3. Check that Template ID 'template_wwrvwq9' exists
4. Ensure no domain restrictions in EmailJS settings
```

**If successful:**
```
📧 Sending email via EmailJS...
Service ID: service_9dcxbjn
Template ID: template_wwrvwq9
Public Key: gzXsWJdlnT8G5go2j
✓ Email sent successfully via EmailJS
Response: OK
```

---

## Quick Checklist

Before testing again, verify:

- [ ] Copied EXACT Public Key from EmailJS Account → General
- [ ] No extra spaces or quotes in the key
- [ ] Service ID matches dashboard exactly
- [ ] Template ID matches dashboard exactly
- [ ] Service is Active (green in dashboard)
- [ ] Template is Active (not draft)
- [ ] Template has all required variables
- [ ] No domain restrictions (or localhost is allowed)
- [ ] Haven't exceeded quota (200 emails/month free)
- [ ] Rebuilt the app after changing credentials

---

## Still Not Working?

### Test Credentials Directly

Use this curl command to test your credentials:

```bash
curl -X POST https://api.emailjs.com/api/v1.0/email/send \
  -H "Content-Type: application/json" \
  -d '{
    "service_id": "service_9dcxbjn",
    "template_id": "template_wwrvwq9",
    "user_id": "YOUR_PUBLIC_KEY_HERE",
    "template_params": {
      "from_name": "Test User",
      "from_email": "test@example.com",
      "subject": "Test Subject",
      "message": "Test message",
      "to_email": "contact@rexinnov.com"
    }
  }'
```

**If you get 200 OK:** Your credentials work! The issue is in the code.
**If you get 403:** Your credentials are wrong. Double-check them.

---

## Alternative: Create New EmailJS Account

If still stuck, start fresh:

1. **Create new EmailJS account**
2. **Connect email service** (Gmail recommended)
3. **Create new template** with these variables:
   ```
   {{from_name}}
   {{from_email}}
   {{subject}}
   {{message}}
   ```
4. **Get NEW credentials:**
   - Service ID
   - Template ID
   - Public Key
5. **Update EmailService.kt** with NEW values
6. **Test**

---

## Most Likely Solution

**99% of 403 errors are due to wrong Public Key.**

1. Go to EmailJS dashboard
2. Account → General
3. Copy Public Key EXACTLY
4. Replace in EmailService.kt
5. Rebuild and test

That should fix it! 🎉

---

## Need More Help?

1. Check EmailJS Dashboard → **Email Logs** to see what error they're showing
2. Contact EmailJS support with your Service ID
3. Share the console output from the updated code above

The detailed logging I just added will show you EXACTLY what's wrong!

