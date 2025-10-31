package com.example.myschool.data

// Represents a single user of our app
data class User(
    val id: String,
    val name: String,
    val gradeLevel: Int
)

// Represents a single subject
data class Subject(
    val id: String,
    val name: String,
    val icon: String, // We'll use this for an icon later
    val progress: Int // A value from 0 to 100
)
