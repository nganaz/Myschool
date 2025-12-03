package com.example.myschool.screens

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myschool.data.UserDataRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class AccountUiState(
    val displayName: String? = null,
    val email: String? = null,
    val photoUrl: Uri? = null,
    val isSignedOut: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AccountViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val storage = Firebase.storage

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    init {
        val user = auth.currentUser
        _uiState.value = AccountUiState(
            displayName = user?.displayName,
            email = user?.email,
            photoUrl = user?.photoUrl
        )
    }

    fun signOut() {
        viewModelScope.launch {
            auth.signOut()
            UserDataRepository.clearData()
            _uiState.value = AccountUiState(isSignedOut = true)
        }
    }

    fun updateProfile(displayName: String, photoUri: Uri?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val user = auth.currentUser ?: return@launch
            var photoUrl: Uri? = user.photoUrl

            try {
                if (photoUri != null) {
                    photoUrl = uploadProfilePicture(user.uid, photoUri)
                }

                val profileUpdates = userProfileChangeRequest {
                    this.displayName = displayName
                    this.photoUri = photoUrl
                }

                user.updateProfile(profileUpdates).await()
                _uiState.value = _uiState.value.copy(
                    displayName = displayName,
                    photoUrl = photoUrl,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    private suspend fun uploadProfilePicture(userId: String, uri: Uri): Uri? {
        val storageRef = storage.reference.child("profile_pictures/$userId.jpg")
        val uploadTask = storageRef.putFile(uri).await()
        return uploadTask.storage.downloadUrl.await()
    }
}