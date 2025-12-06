# 🔧 Quick Fix: EmailJS 403 Forbidden Error

## What You're Seeing
```
✗ Failed to send email: 403 Forbidden
```

## What It Means
**EmailJS is rejecting your credentials.** 99% of the time it's the Public Key.

---

## 3-Step Fix (Takes 2 minutes)

### Step 1: Get Your REAL Public Key
1. Open [EmailJS Dashboard](https://dashboard.emailjs.com/)
2. Click your **account icon** (top right)
3. Click **"Account"** → **"General"**
4. Find **"Public Key"** or **"User ID"**
5. **Click "Copy"** button or select and copy the text

**It looks like:**
- `user_abc123xyz456` OR
- `abc123xyz456def789`

### Step 2: Update Your Code
1. Open: `/Volumes/files/innovrex/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/contactus/service/EmailService.kt`
2. Find line ~32
3. Replace:

**BEFORE:**
```kotlin
const val PUBLIC_KEY = "gzXsWJdlnT8G5go2j"  // ❌ This is wrong
```

**AFTER:**
```kotlin
const val PUBLIC_KEY = "user_abc123xyz456"  // ✅ Paste YOUR key here
```

### Step 3: Rebuild & Test
```bash
./gradlew clean
./gradlew :composeApp:run
```

Fill out the contact form and click "Send Message"

---

## ✅ Success Looks Like:
```
📧 Sending email via EmailJS...
Service ID: service_9dcxbjn
Template ID: template_wwrvwq9
Public Key: user_abc123xyz456
✓ Email sent successfully via EmailJS
Response: OK
```

---

## ❌ Still Getting 403?

### Check Service ID
1. EmailJS Dashboard → **Email Services**
2. Copy the Service ID (should be `service_xxxxxxx`)
3. Update in EmailService.kt if different

### Check Template ID
1. EmailJS Dashboard → **Email Templates**
2. Copy the Template ID (should be `template_xxxxxxx`)
3. Update in EmailService.kt if different

### Check Service Status
- Green indicator = Active ✅
- Red indicator = Inactive ❌ (click to activate)

### Remove Domain Restrictions
1. EmailJS Dashboard → **Account** → **Security**
2. **"Allowed Origins"** → Remove all (or add `http://localhost`)

---

## Test With Curl (Advanced)

Test your credentials directly:

```bash
curl -X POST https://api.emailjs.com/api/v1.0/email/send \
  -H "Content-Type: application/json" \
  -d '{
    "service_id": "service_9dcxbjn",
    "template_id": "template_wwrvwq9",
    "user_id": "PASTE_YOUR_PUBLIC_KEY_HERE",
    "template_params": {
      "from_name": "Test",
      "from_email": "test@test.com",
      "subject": "Test",
      "message": "Test",
      "to_email": "contact@rexinnov.com"
    }
  }'
```

**Response:**
- `"OK"` = Credentials work! ✅
- `403` = Wrong credentials ❌

---

## Need More Help?

📖 **Full guide:** `/docs/EMAILJS_403_ERROR_FIX.md`

🐛 **Console logging:** I've added detailed error output. Check the console when you test!

💡 **99% Fix:** Just update the Public Key from EmailJS dashboard.

---

**Bottom Line:** Copy your EXACT Public Key from EmailJS Account settings and paste it in EmailService.kt line 32. That's it! 🎉

