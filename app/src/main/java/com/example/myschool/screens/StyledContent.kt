package com.example.myschool.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

/**
 * A composable that takes a plain text string and styles it based on simple markup rules.
 * This allows for rich text display from a simple string resource.
 */
@Composable
fun StyledContent(text: String, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    val annotatedString = buildAnnotatedString {
        text.lines().forEach { line ->
            val trimmedLine = line.trim()
            when {
                // Rule for numbered headings (e.g., "1. Noun:")
                trimmedLine.matches(Regex("^\\d+\\.\\s+.*:")) -> {
                    withStyle(style = typography.titleMedium.toSpanStyle().copy(fontWeight = FontWeight.Bold)) {
                        append(trimmedLine + "\n")
                    }
                }

                // Rule for sub-headings (e.g., "Key Themes:")
                trimmedLine.endsWith(":") && !trimmedLine.matches(Regex("^\\d+\\.\\s+.*")) && !trimmedLine.startsWith("-") -> {
                    withStyle(style = typography.titleSmall.toSpanStyle().copy(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append(trimmedLine + "\n")
                    }
                }

                // Rule for bullet points (e.g., "-   Examples: ...")
                trimmedLine.startsWith("-") -> {
                    append("  ") // Indent for the bullet point
                    withStyle(style = typography.bodyLarge.toSpanStyle()) {
                        append("• " + trimmedLine.removePrefix("-").trim() + "\n")
                    }
                }

                // Rule for a normal paragraph of text
                trimmedLine.isNotBlank() -> {
                    withStyle(style = typography.bodyLarge.toSpanStyle()) {
                        append(trimmedLine + "\n")
                    }
                }

                // Preserve blank lines for spacing
                else -> {
                    append("\n")
                }
            }
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground
    )
}
