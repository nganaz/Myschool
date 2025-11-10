package com.example.myschool.data

data class Response(
    val id: Int,
    val questionId: Int,
    val author: String,
    val authorImageUrl: String,
    val date: String,
    val response: String,
    val likes: Int
)