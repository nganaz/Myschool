package com.example.myschool.screens

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myschool.R

@Composable
fun LoginScreen(
    onLoginSuccess: (isNewUser: Boolean) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            loginViewModel.onGoogleSignInResult(result.data, context)
        }
    }

    LaunchedEffect(uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) {
            onLoginSuccess(uiState.isNewUser)
        }
    }

    LaunchedEffect(uiState.loginError) {
        uiState.loginError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(uiState.passwordResetEmailSent) {
        if (uiState.passwordResetEmailSent) {
            Toast.makeText(context, "Password reset email sent", Toast.LENGTH_LONG).show()
            loginViewModel.resetPasswordResetEmailSent()
        }
    }

    LaunchedEffect(uiState.googleSignInIntentSender) {
        uiState.googleSignInIntentSender?.let {
            launcher.launch(IntentSenderRequest.Builder(it).build())
            loginViewModel.resetGoogleSignInIntentSender()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        IconButton(
            onClick = {
                if (uiState.isLoginView) {
                    if (!navController.popBackStack()) {
                        navController.navigate("welcome") {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                } else {
                    loginViewModel.toggleIsLoginView()
                }
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(id = R.string.login))
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(if (uiState.isLoginView) stringResource(id = R.string.login) else stringResource(id = R.string.register), fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(32.dp))

            if (!uiState.isLoginView) {
                OutlinedTextField(
                    value = uiState.username,
                    onValueChange = { loginViewModel.onUsernameChange(it) },
                    label = { Text(stringResource(id = R.string.username)) },
                    isError = uiState.usernameError != null,
                    supportingText = { uiState.usernameError?.let { Text(it) } },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { loginViewModel.onEmailChange(it) },
                label = { Text(stringResource(id = R.string.email)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = uiState.emailError != null,
                supportingText = { uiState.emailError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { loginViewModel.onPasswordChange(it) },
                label = { Text(if (uiState.isLoginView) stringResource(id = R.string.password) else stringResource(id = R.string.create_password)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = uiState.passwordError != null,
                supportingText = { uiState.passwordError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (uiState.isLoginView) {
                        loginViewModel.onLoginClicked()
                    } else {
                        loginViewModel.onRegisterClicked()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(if (uiState.isLoginView) stringResource(id = R.string.login_button) else stringResource(id = R.string.create_account_button), color = Color.Black)
            }

            if (uiState.isLoginView) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.forgot_password), fontSize = 14.sp)
                    TextButton(onClick = { loginViewModel.onForgotPasswordClicked() }) {
                        Text(
                            text = stringResource(R.string.reset),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(if (uiState.isLoginView) stringResource(id = R.string.or_sign_in_with) else stringResource(id = R.string.or_sign_up_with))
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { loginViewModel.onSignInWithGoogleClicked(context) },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(id = R.string.sign_in_with_google),
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Text(if (uiState.isLoginView) stringResource(id = R.string.sign_in_with_google) else stringResource(id = R.string.sign_up_with_google), modifier = Modifier.padding(start = 8.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = if (uiState.isLoginView) stringResource(id = R.string.dont_have_an_account) else stringResource(id = R.string.already_a_user), fontSize = 14.sp)
                Text(
                    text = if (uiState.isLoginView) stringResource(id = R.string.register) else stringResource(id = R.string.login),
                    modifier = Modifier.clickable { loginViewModel.toggleIsLoginView() },
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
