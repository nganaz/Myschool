package com.example.myschool.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController, infoType: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = infoType ?: "Info") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            when (infoType) {
                "Help" -> HelpContent()
                "About" -> AboutContent()
                else -> Text("Invalid Info Type")
            }
        }
    }
}

@Composable
fun HelpContent() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Frequently Asked Questions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "How do I check my grades?",
                fontWeight = FontWeight.Bold
            )
            Text(text = "Navigate to the 'Account' screen to see your recent grades and overall performance.")
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "How do I submit an assignment?",
                fontWeight = FontWeight.Bold
            )
            Text(text = "You can submit assignments through the respective subject's screen. Look for the 'Assignments' section.")
        }
    }
}

@Composable
fun AboutContent() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "About MySchool App",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Version: 1.0.0", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Developer: Atusweghe Mwenengana", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Contact: 0997021799 / 0888295642", fontSize = 16.sp)
        }
    }
}
