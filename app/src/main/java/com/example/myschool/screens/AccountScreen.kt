package com.example.myschool.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myschool.R

@Composable
fun AccountScreen(
    navController: NavController,
    onSignOut: () -> Unit,
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    val accountViewModel: AccountViewModel = viewModel()
    val uiState by accountViewModel.uiState.collectAsState()
    var notificationsEnabled by remember { mutableStateOf(true) }
    var showEditProfileDialog by remember { mutableStateOf(false) }
    var newDisplayName by remember(uiState.displayName) { mutableStateOf(uiState.displayName ?: "") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )

    LaunchedEffect(uiState.isSignedOut) {
        if (uiState.isSignedOut) {
            onSignOut()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    if (showEditProfileDialog) {
        AlertDialog(
            onDismissRequest = { if (!uiState.isLoading) showEditProfileDialog = false },
            title = { Text("Edit Profile") },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = newDisplayName,
                        onValueChange = { newDisplayName = it },
                        label = { Text("New display name") },
                        enabled = !uiState.isLoading
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(onClick = { imagePickerLauncher.launch("image/*") }, enabled = !uiState.isLoading) {
                        Text("Select Profile Picture")
                    }
                    selectedImageUri?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = "Selected profile picture",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(top = 8.dp)
                        )
                    }
                    if (uiState.isLoading) {
                        Spacer(modifier = Modifier.height(8.dp))
                        CircularProgressIndicator()
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        accountViewModel.updateProfile(newDisplayName, selectedImageUri)
                    },
                    enabled = !uiState.isLoading
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showEditProfileDialog = false },
                    enabled = !uiState.isLoading
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    // Close dialog on successful update
    LaunchedEffect(uiState.photoUrl, uiState.displayName) {
        if (!uiState.isLoading && uiState.error == null) {
            showEditProfileDialog = false
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("Account", style = MaterialTheme.typography.headlineMedium)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 16.dp)
        ) {
            item {
                // Profile Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    AsyncImage(
                        model = uiState.photoUrl,
                        contentDescription = "User Avatar",
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                        error = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = uiState.displayName ?: "User",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = uiState.email ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { showEditProfileDialog = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
                    }
                }
            }

            item {
                // Settings Section
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Settings Items
            item {
                SettingItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    trailingContent = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Default.DarkMode,
                    title = "Dark Mode",
                    trailingContent = {
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { onToggleDarkMode() }
                        )
                    }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Default.Language,
                    title = "Language",
                    onClick = { /* Handle language selection */ }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Default.PrivacyTip,
                    title = "Privacy Policy",
                    onClick = { /* Handle privacy policy navigation */ }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Default.ContactSupport,
                    title = "Contact Us",
                    onClick = { /* Handle contact us navigation */ }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Default.Star,
                    title = "Rate This App",
                    onClick = { /* Handle app rating */ }
                )
            }
            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
            item {
                Button(
                    onClick = { accountViewModel.signOut() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.Default.Logout, contentDescription = "Log Out")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Log Out")
                    }
                }
            }
        }
    }
}

@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    onClick: (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = { onClick?.invoke() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(icon, contentDescription = title)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1f))
            if (trailingContent != null) {
                trailingContent()
            } else if (onClick != null) {
                Icon(Icons.Default.ArrowForwardIos, contentDescription = "Navigate")
            }
        }
    }
}