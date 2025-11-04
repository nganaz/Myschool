package com.example.myschool.screens.subjects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myschool.data.DataSource

@Composable
fun TopicScreen(subjectId: String, topicId: String, modifier: Modifier = Modifier) {
    val subject = DataSource.getSubjectById(subjectId)
    val topic = subject?.topics?.find { it.id == topicId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (topic != null) {
            Text(text = topic.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = topic.content, style = MaterialTheme.typography.bodyLarge)
        } else {
            Text(text = "Topic not found.", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
