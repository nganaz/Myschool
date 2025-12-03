package com.example.myschool.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.math.abs

private val avatarColors = listOf(
    Color(0xFFF44336),
    Color(0xFFE91E63),
    Color(0xFF9C27B0),
    Color(0xFF673AB7),
    Color(0xFF3F51B5),
    Color(0xFF2196F3),
    Color(0xFF00BCD4),
    Color(0xFF009688),
    Color(0xFF4CAF50),
    Color(0xFFFF9800),
    Color(0xFF795548),
    Color(0xFF607D8B)
)

fun generateColor(text: String): Color {
    val hash = abs(text.hashCode())
    return avatarColors[hash % avatarColors.size]
}
