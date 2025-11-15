package com.example.myschool.screens

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class AccountState(
    val email: String? = null,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val isSignedOut: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AccountViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val storage = Firebase.storage

    private val _uiState = MutableStateFlow(AccountState())
    val uiState = _uiState.asStateFlow()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        val user = auth.currentUser
        _uiState.update {
            it.copy(
                email = user?.email,
                displayName = user?.displayName,
                photoUrl = user?.photoUrl?.toString()
            )
        }
    }

    fun signOut() {
        viewModelScope.launch {
            auth.signOut()
            _uiState.update { it.copy(isSignedOut = true) }
        }
    }

    fun updateProfile(displayName: String, photoUri: Uri?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                photoUri?.let {
                    val imageUrl = uploadProfileImage(it)
                    updateProfile(displayName, imageUrl)
                } ?: updateProfile(displayName, uiState.value.photoUrl)
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private suspend fun uploadProfileImage(uri: Uri): String {
        val user = auth.currentUser!!
        val storageRef = storage.reference.child("profile_images/${user.uid}")
        storageRef.putFile(uri).await()
        return storageRef.downloadUrl.await().toString()
    }

    private fun updateProfile(displayName: String, photoUrl: String?) {
        val user = auth.currentUser!!
        val profileUpdates = userProfileChangeRequest {
            this.displayName = displayName
            this.photoUri = photoUrl?.let { Uri.parse(it) }
        }
        user.updateProfile(profileUpdates).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getCurrentUser()
                _uiState.update { it.copy(isLoading = false) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = task.exception?.message) }
            }
        }
    }
}
