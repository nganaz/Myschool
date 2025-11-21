package com.example.myschool.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NotificationRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications

    private val _notificationCount = MutableStateFlow(0)
    val notificationCount: StateFlow<Int> = _notificationCount

    fun fetchNotifications() {
        val userId = auth.currentUser?.uid ?: return
        db.collection("notifications")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val notificationList = snapshot.toObjects(Notification::class.java)
                    _notifications.value = notificationList
                    _notificationCount.value = notificationList.count { !it.isRead }
                }
            }
    }

    fun addNotification(notification: Notification) {
        db.collection("notifications").add(notification)
    }

    fun markNotificationAsRead(notificationId: String) {
        db.collection("notifications").document(notificationId)
            .update("isRead", true)
    }

    fun clearNotificationCount() {
        _notificationCount.value = 0
    }
}
