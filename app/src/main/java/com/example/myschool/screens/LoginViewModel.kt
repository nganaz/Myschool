package com.example.myschool.screens

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myschool.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class LoginScreenState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val isLoginView: Boolean = true,
    val emailError: String? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val isLoginSuccess: Boolean = false,
    val isNewUser: Boolean = false,
    val googleSignInIntentSender: IntentSender? = null,
    val passwordResetEmailSent: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _uiState = MutableStateFlow(LoginScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
        if (_uiState.value.emailError != null) {
            validateEmail()
        }
    }

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
        if (_uiState.value.usernameError != null) {
            validateUsername()
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
        if (_uiState.value.passwordError != null) {
            validatePassword()
        }
    }

    fun toggleIsLoginView() {
        _uiState.update { it.copy(
            isLoginView = !it.isLoginView,
            emailError = null,
            usernameError = null,
            passwordError = null,
            loginError = null
        ) }
    }

    fun onLoginClicked() {
        if (validateForm()) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true, loginError = null) }
                try {
                    auth.signInWithEmailAndPassword(uiState.value.email, uiState.value.password).await()
                    _uiState.update { it.copy(isLoading = false, isLoginSuccess = true, isNewUser = false) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(isLoading = false, loginError = e.message) }
                }
            }
        }
    }

    fun onRegisterClicked() {
        if (validateForm()) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true, loginError = null) }
                try {
                    val authResult = auth.createUserWithEmailAndPassword(uiState.value.email, uiState.value.password).await()
                    val user = authResult.user
                    val profileUpdates = userProfileChangeRequest {
                        displayName = uiState.value.username
                    }
                    user?.updateProfile(profileUpdates)?.await()
                    _uiState.update { it.copy(isLoading = false, isLoginSuccess = true, isNewUser = true) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(isLoading = false, loginError = e.message) }
                }
            }
        }
    }

    fun onForgotPasswordClicked() {
        if (validateEmail()) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true, loginError = null, passwordResetEmailSent = false) }
                try {
                    auth.sendPasswordResetEmail(uiState.value.email).await()
                    _uiState.update { it.copy(isLoading = false, passwordResetEmailSent = true) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(isLoading = false, loginError = e.message) }
                }
            }
        }
    }

    fun resetPasswordResetEmailSent() {
        _uiState.update { it.copy(passwordResetEmailSent = false) }
    }

    fun onSignInWithGoogleClicked(context: Context) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loginError = null) }
            try {
                val oneTapClient = Identity.getSignInClient(context)
                val signInRequest = BeginSignInRequest.builder()
                    .setGoogleIdTokenRequestOptions(
                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                            .setSupported(true)
                            .setServerClientId(context.getString(R.string.default_web_client_id))
                            .setFilterByAuthorizedAccounts(false)
                            .build()
                    )
                    .build()
                val result = oneTapClient.beginSignIn(signInRequest).await()
                _uiState.update { it.copy(googleSignInIntentSender = result.pendingIntent.intentSender) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, loginError = e.message) }
            }
        }
    }

    fun onGoogleSignInResult(intent: Intent?, context: Context) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loginError = null) }
            try {
                val oneTapClient = Identity.getSignInClient(context)
                val credential = oneTapClient.getSignInCredentialFromIntent(intent)
                val googleIdToken = credential.googleIdToken
                if (googleIdToken != null) {
                    val googleAuthCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                    firebaseAuthWithGoogle(googleAuthCredential)
                } else {
                    _uiState.update { it.copy(isLoading = false, loginError = "Google Sign-In failed") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, loginError = e.message) }
            }
        }
    }

    private fun firebaseAuthWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            try {
                val authResult = auth.signInWithCredential(credential).await()
                val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
                _uiState.update { it.copy(isLoading = false, isLoginSuccess = true, isNewUser = isNewUser) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, loginError = e.message) }
            }
        }
    }

    fun resetGoogleSignInIntentSender() {
        _uiState.update { it.copy(googleSignInIntentSender = null) }
    }

    private fun validateEmail(): Boolean {
        val email = _uiState.value.email
        return if (email.isBlank()) {
            _uiState.update { it.copy(emailError = "Email cannot be empty") }
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.update { it.copy(emailError = "Invalid email format") }
            false
        } else {
            _uiState.update { it.copy(emailError = null) }
            true
        }
    }

    private fun validateUsername(): Boolean {
        return if (!uiState.value.isLoginView && uiState.value.username.isBlank()) {
            _uiState.update { it.copy(usernameError = "Username cannot be empty") }
            false
        } else {
            _uiState.update { it.copy(usernameError = null) }
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = _uiState.value.password
        return if (password.isBlank()) {
            _uiState.update { it.copy(passwordError = "Password cannot be empty") }
            false
        } else if (password.length < 6) {
            _uiState.update { it.copy(passwordError = "Password must be at least 6 characters") }
            false
        } else {
            _uiState.update { it.copy(passwordError = null) }
            true
        }
    }

    private fun validateForm(): Boolean {
        val isUsernameValid = validateUsername()
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()
        return isUsernameValid && isEmailValid && isPasswordValid
    }
}
