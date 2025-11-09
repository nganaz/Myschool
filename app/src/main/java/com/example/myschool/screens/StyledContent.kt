package com.example.myschool.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
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
                    withStyle(style = typography.titleMedium.toSpanStyle().copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                        append(trimmedLine + "\n")
                    }
                }

                // Rule for sub-headings (e.g., "Key Themes:")
                trimmedLine.endsWith(":") && !trimmedLine.matches(Regex("^\\d+\\.\\s+.*")) && !trimmedLine.startsWith("-") -> {
                    withStyle(style = typography.titleSmall.toSpanStyle().copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                        append(trimmedLine + "\n")
                    }
                }

                // Rule for bullet points (e.g., "-   Examples: ...")
                trimmedLine.startsWith("-") -> {
                    append("  • ") // Indent for the bullet point
                    val content = trimmedLine.removePrefix("-").trim()
                    val parts = content.split(Regex("(\\*.*?\\*)"))
                    for (part in parts) {
                        if (part.startsWith("*") && part.endsWith("*")) {
                            withStyle(style = typography.bodyLarge.toSpanStyle().copy(fontWeight = FontWeight.Bold)) {
                                append(part.removeSurrounding("*"))
                            }
                        } else {
                            withStyle(style = typography.bodyLarge.toSpanStyle()) {
                                append(part)
                            }
                        }
                    }
                    append("\n")
                }

                // Rule for a normal paragraph of text
                trimmedLine.isNotBlank() -> {
                    val parts = trimmedLine.split(Regex("(\\*.*?\\*)"))
                    for (part in parts) {
                        if (part.startsWith("*") && part.endsWith("*")) {
                            withStyle(style = typography.bodyLarge.toSpanStyle().copy(fontWeight = FontWeight.Bold)) {
                                append(part.removeSurrounding("*"))
                            }
                        } else {
                            withStyle(style = typography.bodyLarge.toSpanStyle()) {
                                append(part)
                            }
                        }
                    }
                    append("\n")
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
