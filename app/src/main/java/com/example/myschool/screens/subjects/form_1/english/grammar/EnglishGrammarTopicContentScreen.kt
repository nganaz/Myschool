package com.example.myschool.screens.subjects.form_1.english.grammar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myschool.data.englishGrammarTopics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnglishGrammarTopicContentScreen(navController: NavController, topicId: String?) {
    val topic = englishGrammarTopics.find { it.id == topicId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = topic?.name ?: "Topic Content") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (topic != null) {
                Text(text = topic.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = topic.content, style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = "Topic not found", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}