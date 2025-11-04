package com.example.myschool.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NotificationsScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Notifications", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            // Add a placeholder for notifications
            items(3) { index ->
                Text("Notification ${index + 1}", modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
