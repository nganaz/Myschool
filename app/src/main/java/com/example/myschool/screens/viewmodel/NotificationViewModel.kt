package com.example.myschool.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myschool.data.Notification
import com.example.myschool.data.NotificationRepository
import com.example.myschool.data.User
import com.example.myschool.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {
    val notifications: StateFlow<List<Notification>> = NotificationRepository.notifications
    val notificationCount: StateFlow<Int> = NotificationRepository.notificationCount

    private val _users = MutableStateFlow<Map<String, User>>(emptyMap())
    val users: StateFlow<Map<String, User>> = _users.asStateFlow()

    init {
        NotificationRepository.fetchNotifications()

        notifications.onEach { notificationList ->
            val userIds = notificationList.map { it.userId }.distinct()
            userIds.forEach { userId ->
                if (!_users.value.containsKey(userId)) {
                    viewModelScope.launch {
                        UserRepository.getUser(userId)?.let { user ->
                            _users.value = _users.value + (userId to user)
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun markNotificationAsRead(notificationId: String) {
        viewModelScope.launch {
            NotificationRepository.markNotificationAsRead(notificationId)
        }
    }

    fun clearNotifications() {
        NotificationRepository.clearNotificationCount()
    }
}
