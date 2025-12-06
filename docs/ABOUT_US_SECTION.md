# About Us Section - Implementation Guide

## Overview

The About Us section has been successfully created and integrated into the HomeScreen. It displays company information with a responsive layout that adapts to different screen sizes.

---

## 📁 File Structure

```
composeApp/src/commonMain/kotlin/com/hp/innovrex/features/
├── aboutus/
│   └── ui/
│       └── AboutUsSection.kt  ✅ NEW
└── home/
    └── ui/
        └── HomeScreen.kt      ✅ UPDATED
```

---

## 🎨 Component Details

### AboutUsSection.kt

**Location:** `/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/aboutus/ui/AboutUsSection.kt`

#### Features:
- **Responsive Layout**: Automatically switches between mobile (stacked) and desktop (side-by-side) layouts
- **Professional Typography**: Uses Material Design typography with custom sizing
- **Team Image Placeholder**: Includes a placeholder for team collaboration image
- **Consistent Spacing**: Uses design system spacing tokens

#### Layout Modes:

**Desktop/Tablet Layout:**
```
┌────────────────────────────────────────────────────────┐
│                                                        │
│  ┌──────────────────┐  ┌────────────────────────┐    │
│  │  About Us        │  │                        │    │
│  │                  │  │   Team Image           │    │
│  │  Description 1   │  │   (Placeholder)        │    │
│  │                  │  │                        │    │
│  │  Description 2   │  │                        │    │
│  └──────────────────┘  └────────────────────────┘    │
│                                                        │
└────────────────────────────────────────────────────────┘
```

**Mobile Layout:**
```
┌──────────────────────┐
│  About Us            │
│                      │
│  Description 1       │
│                      │
│  Description 2       │
│                      │
│  ┌────────────────┐  │
│  │  Team Image    │  │
│  │  (Placeholder) │  │
│  └────────────────┘  │
└──────────────────────┘
```

---

## 📝 Content

### Title
- **Text**: "About Us"
- **Style**: Display Small, Bold, 36sp
- **Color**: Gray900

### Paragraph 1
"We are a Kotlin Multiplatform (KMP)-based software development company specializing in creating cross-platform applications that run seamlessly across mobile, web, and desktop environments."

### Paragraph 2
"Our mission is to simplify development through a shared codebase, delivering consistent performance and experience across platforms — Android, iOS, Windows, and macOS."

---

## 🔧 Integration with HomeScreen

### Changes Made:

1. **Import Added:**
   ```kotlin
   import com.hp.innovrex.features.aboutus.ui.AboutUsSection
   ```

2. **Section Replaced:**
   - Old: `AboutSection(screenSize = screenSize)` (placeholder)
   - New: `AboutUsSection(screenSize = screenSize)` (full implementation)

3. **Position in Layout:**
   - Appears after the Products section
   - Before the Contact section
   - Fully integrated with navigation system

---

## 🎯 Design Specifications

### Spacing:
- **Section Padding (Horizontal)**:
  - Mobile: `SpacingTokens.LG`
  - Tablet: `SpacingTokens.XXL`
  - Desktop: `SpacingTokens.Giant`
- **Section Padding (Vertical)**: `SpacingTokens.Huge`
- **Content Spacing**: `SpacingTokens.MD` between elements

### Colors:
- **Background**: `BrandColors.White`
- **Title**: `BrandColors.Gray900`
- **Body Text**: `BrandColors.Gray700`
- **Image Placeholder Background**: `BrandColors.Gray200`
- **Image Placeholder Text**: `BrandColors.Gray600`

### Typography:
- **Title**: 
  - Font Weight: Bold
  - Size: 36sp
  - Style: Display Small
- **Body Text**: 
  - Font Weight: Regular
  - Size: 16sp
  - Line Height: 24sp
  - Style: Body Large

### Image:
- **Aspect Ratio**: 1.5:1 (width:height)
- **Corner Radius**: 16dp
- **Content Scale**: Crop (when image is added)

---

## 🚀 Usage

The About Us section is automatically displayed on the HomeScreen. No additional configuration is needed.

### Navigation:
Users can navigate to the About Us section by:
1. Clicking "About" in the navigation bar
2. Scrolling down the page
3. Using the scroll navigation system

---

## 📋 TODO

### Add Team Image:
Currently using a placeholder. To add the actual team collaboration image:

1. **Add Image Resource:**
   - Place image in: `composeApp/src/commonMain/composeResources/drawable/`
   - Recommended name: `team_collaboration.png` or `team_collaboration.jpg`
   - Recommended size: 1200x800 pixels (1.5:1 ratio)

2. **Update Code:**
   ```kotlin
   import androidx.compose.foundation.Image
   import androidx.compose.ui.layout.ContentScale
   import org.jetbrains.compose.resources.painterResource
   import innovrex.composeapp.generated.resources.Res
   import innovrex.composeapp.generated.resources.team_collaboration
   
   // In TeamImage composable:
   Image(
       painter = painterResource(Res.drawable.team_collaboration),
       contentDescription = "Team collaboration",
       modifier = Modifier.fillMaxSize(),
       contentScale = ContentScale.Crop
   )
   ```

---

## ✅ Build Status

```
> Task :composeApp:compileKotlinWasmJs UP-TO-DATE
BUILD SUCCESSFUL
```

The About Us section compiles successfully and is ready for use!

---

## 🎨 Visual Preview

Based on the provided design, the About Us section features:
- Clean, professional typography
- Responsive layout that works on all screen sizes
- Clear hierarchy with bold title and readable body text
- Placeholder for team collaboration image
- Consistent spacing and alignment
- White background for clean appearance

---

## 📊 Summary

✅ **AboutUsSection.kt created** - Full responsive component  
✅ **HomeScreen.kt updated** - Integrated new section  
✅ **Responsive layouts** - Mobile and desktop variants  
✅ **Design system integration** - Uses spacing and color tokens  
✅ **Navigation ready** - Works with scroll navigation  
✅ **Compilation verified** - Build successful  
✅ **Image placeholder** - Ready for team photo  

**The About Us section is now live on the HomeScreen!** 🎉

---

## 🔗 Related Files

- `/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/aboutus/ui/AboutUsSection.kt`
- `/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/home/ui/HomeScreen.kt`
- `/composeApp/src/commonMain/kotlin/com/hp/innovrex/features/home/ui/components/Sections.kt`

---

## 📱 Testing

To see the About Us section in action:

```bash
cd /Volumes/files/innovrex
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

Then navigate to: **http://localhost:8080**

Scroll down or click "About" in the navigation to view the section.

