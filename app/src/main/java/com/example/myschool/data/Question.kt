package com.example.myschool.data

data class Question(
    val id: Int,
    val subject: String,
    val question: String,
    val author: String,
    val authorImageUrl: String,
    val date: String,
    val comments: Int,
    val likes: Int
)
