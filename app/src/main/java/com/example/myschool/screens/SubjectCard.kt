package com.example.myschool.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myschool.data.Subject

@Composable
fun SubjectCard(subject: Subject) {
    // Card provides a nice, elevated container for our content.
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Row for Icon and Subject Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = subject.icon, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = subject.name, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row for Progress Bar and Percentage Text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // The LinearProgressIndicator shows the completion status.
                LinearProgressIndicator(
                    progress = { subject.progress / 100f }, // Progress must be a Float between 0.0 and 1.0
                    modifier = Modifier.weight(1f) // The bar takes up most of the space
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "${subject.progress}%",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
