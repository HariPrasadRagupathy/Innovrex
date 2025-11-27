package com.hp.innovrex.showcase.tokens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hp.innovrex.designsystem.tokens.foundation.TypographyTokens
import com.hp.innovrex.showcase.ComponentCategory
import com.hp.innovrex.showcase.ComponentItem

val TypographyTokensShowcase = ComponentItem(
    name = "Typography",
    description = "Type scale used across headings, body, and labels.",
    category = ComponentCategory.TOKEN,
    preview = { TypographyPreview() },
    controls = { TypographyDescription() }
)

@Composable
private fun TypographyPreview() {
    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Headline Large", style = TypographyTokens.HeadlineLarge)
        Text("Headline Medium", style = TypographyTokens.HeadlineMedium)
        Text("Headline Small", style = TypographyTokens.HeadlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Title Large", style = TypographyTokens.TitleLarge)
        Text("Title Medium", style = TypographyTokens.TitleMedium)
        Text("Title Small", style = TypographyTokens.TitleSmall)
        Spacer(Modifier.height(8.dp))
        Text("Body Large", style = TypographyTokens.BodyLarge)
        Text("Body Medium", style = TypographyTokens.BodyMedium)
        Text("Body Small", style = TypographyTokens.BodySmall)
        Spacer(Modifier.height(8.dp))
        Text("Label Large", style = TypographyTokens.LabelLarge)
        Text("Label Medium", style = TypographyTokens.LabelMedium)
        Text("Label Small", style = TypographyTokens.LabelSmall)
    }
}

@Composable
private fun TypographyDescription() {
    Text("Semantic mapping aligns with Material3 spec; override FontFamily for brand.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

