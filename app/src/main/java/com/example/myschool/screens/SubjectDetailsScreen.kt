package com.example.myschool.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myschool.data.DataSource
import com.example.myschool.data.Topic

@Composable
fun SubjectDetailsScreen(
    subjectId: String,
    onTopicClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val subject = DataSource.getSubjectById(subjectId)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (subject != null) {
            Text(text = subject.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(subject.topics) { topic ->
                    TopicListItem(topic = topic, onTopicClick = { onTopicClick(topic.id) })
                }
            }
        } else {
            Text(text = "Subject not found.", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun TopicListItem(topic: Topic, onTopicClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onTopicClick)
    ) {
        Text(
            text = topic.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}