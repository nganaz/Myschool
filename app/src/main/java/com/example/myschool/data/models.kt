package com.example.myschool.data

import androidx.compose.ui.graphics.Color

// Represents a single user of our app
data class User(
    val id: String,
    val name: String,
    val gradeLevel: Int
)

// Represents a topic within a subject
data class Topic(
    val id: String,
    val name: String,
    val content: String // For now, this can be a simple string. Later, it could be more complex.
)


// Represents a single subject
data class Subject(
    val id: String,
    val name: String,
    val icon: String, // We'll use this for an icon later
    val progress: Int, // A value from 0 to 100
    val color: Color
)
