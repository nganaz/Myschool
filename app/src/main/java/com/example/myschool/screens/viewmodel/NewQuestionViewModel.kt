package com.example.myschool.screens.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myschool.data.Notification
import com.example.myschool.data.NotificationRepository
import com.example.myschool.data.Question
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewQuestionViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun addQuestion(question: Question, onSuccess: () -> Unit) {
        db.collection("questions")
            .add(question)
            .addOnSuccessListener { 
                val userId = auth.currentUser?.uid ?: return@addOnSuccessListener
                val notification = Notification(
                    userId = userId,
                    message = "Your question has been posted successfully.",
                    timestamp = System.currentTimeMillis()
                )
                NotificationRepository.addNotification(notification)
                onSuccess()
            }
            .addOnFailureListener { 
                // Handle error
            }
    }
}