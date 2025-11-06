package com.example.myschool.screens.subjects.form_1.english.literature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myschool.data.findTopicById
import com.example.myschool.screens.StyledContent
import com.example.myschool.ui.theme.TextPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnglishLiteratureTopicContentScreen(navController: NavController, topicId: String?) {
    val topic = findTopicById(topicId)

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
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (topic != null) {
                Text(text = topic.name, style = MaterialTheme.typography.headlineMedium, color = TextPrimaryColor)
                Spacer(modifier = Modifier.height(16.dp))
                StyledContent(text = topic.content)
            } else {
                Text(text = "Topic not found", style = MaterialTheme.typography.headlineMedium, color = Color.Red)
            }
        }
    }
}