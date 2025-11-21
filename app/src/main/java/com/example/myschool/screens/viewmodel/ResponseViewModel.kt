package com.example.myschool.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myschool.data.Notification
import com.example.myschool.data.NotificationRepository
import com.example.myschool.data.Question
import com.example.myschool.data.Response
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResponseViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val _question = MutableStateFlow<Question?>(null)
    val question: StateFlow<Question?> = _question

    private val _responses = MutableStateFlow<List<Response>>(emptyList())
    val responses: StateFlow<List<Response>> = _responses
    private val auth = Firebase.auth

    fun fetchQuestion(questionId: String) {
        viewModelScope.launch {
            db.collection("questions").document(questionId)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        // Handle error
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        _question.value = snapshot.toObject(Question::class.java)
                    }
                }
        }
    }

    fun fetchResponses(questionId: String) {
        viewModelScope.launch {
            db.collection("responses")
                .whereEqualTo("questionId", questionId)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        // Handle error
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val responseList = snapshot.toObjects(Response::class.java)
                        _responses.value = responseList
                    }
                }
        }
    }

    fun addResponse(response: Response, onSuccess: () -> Unit) {
        db.collection("responses")
            .add(response)
            .addOnSuccessListener {
                db.collection("questions").document(response.questionId)
                    .update("comments", FieldValue.increment(1L))
                val userId = auth.currentUser?.uid ?: return@addOnSuccessListener
                val notification = Notification(
                    userId = userId,
                    message = "Your response has been posted successfully.",
                    timestamp = System.currentTimeMillis()
                )
                NotificationRepository.addNotification(notification)
                onSuccess()
            }
            .addOnFailureListener {
                // Handle error
            }
    }

    fun deleteResponse(responseId: String) {
        viewModelScope.launch {
            db.collection("responses").document(responseId)
                .get()
                .addOnSuccessListener { document ->
                    val response = document.toObject(Response::class.java)
                    if (response != null) {
                        db.collection("questions").document(response.questionId)
                            .update("comments", FieldValue.increment(-1L))
                        db.collection("responses").document(responseId)
                            .delete()
                    }
                }
        }
    }
    
    fun likeResponse(responseId: String, isLiked: Boolean) {
        val userId = auth.currentUser?.uid ?: return
        val responseRef = db.collection("responses").document(responseId)

        if (isLiked) {
            responseRef.update("likedBy", FieldValue.arrayUnion(userId))
            responseRef.update("likes", FieldValue.increment(1L))
        } else {
            responseRef.update("likedBy", FieldValue.arrayRemove(userId))
            responseRef.update("likes", FieldValue.increment(-1L))
        }
    }

    fun addReplyToResponse(responseId: String) {
        db.collection("responses").document(responseId)
            .update("replies", FieldValue.increment(1L))
    }
}