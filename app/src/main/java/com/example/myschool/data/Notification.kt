package com.example.myschool.data

import com.google.firebase.firestore.DocumentId

data class Notification(
    @DocumentId val id: String = "",
    val userId: String = "",
    val message: String = "",
    val timestamp: Long = 0L,
    val isRead: Boolean = false
)
