package com.example.myschool.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myschool.data.Question
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions
    private val auth = Firebase.auth

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            db.collection("questions")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        // Handle error
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val questionList = snapshot.toObjects(Question::class.java)
                        _questions.value = questionList
                    }
                }
        }
    }

    fun deleteQuestion(questionId: String) {
        viewModelScope.launch {
            db.collection("questions").document(questionId)
                .delete()
                .addOnSuccessListener { 
                    // Question deleted successfully
                }
                .addOnFailureListener { 
                    // Handle error
                }
        }
    }

    fun likeQuestion(questionId: String, isLiked: Boolean) {
        val userId = auth.currentUser?.uid ?: return
        val questionRef = db.collection("questions").document(questionId)

        if (isLiked) {
            questionRef.update("likedBy", FieldValue.arrayUnion(userId))
            questionRef.update("likes", FieldValue.increment(1L))
        } else {
            questionRef.update("likedBy", FieldValue.arrayRemove(userId))
            questionRef.update("likes", FieldValue.increment(-1L))
        }
    }

    fun replyToQuestion(questionId: String) {
        db.collection("questions").document(questionId)
            .update("comments", FieldValue.increment(1L))
    }
}