package com.example.myschool.data

import com.google.firebase.firestore.DocumentId

data class Question(
    @DocumentId val id: String = "",
    val subject: String = "",
    val question: String = "",
    val author: String = "",
    val authorId: String = "",
    val authorImageUrl: String = "",
    val date: String = "",
    val comments: Int = 0,
    val likes: Int = 0,
    val likedBy: List<String> = emptyList(),
    val description: String = ""
)
