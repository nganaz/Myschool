package com.example.myschool.data

import com.google.firebase.firestore.DocumentId

data class Response(
    @DocumentId val id: String = "",
    val questionId: String = "",
    val author: String = "",
    val authorId: String = "",
    val authorImageUrl: String = "",
    val date: String = "",
    val response: String = "",
    val likes: Int = 0,
    val likedBy: List<String> = emptyList(),
    val replies: Int = 0
)
