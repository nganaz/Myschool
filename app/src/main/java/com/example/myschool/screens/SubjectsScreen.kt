package com.example.myschool.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myschool.data.DataSource
import com.example.myschool.data.Subject

@Composable
fun SubjectsScreen(
    form: String,
    onSubjectClick: (Subject) -> Unit, 
    modifier: Modifier = Modifier
) {
    val gradeLevel = form.filter { it.isDigit() }.toIntOrNull()
    val subjects = if (gradeLevel != null) DataSource.getSubjectsForGrade(gradeLevel) else emptyList()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (subjects.isNotEmpty()) {
            Text(
                text = "All Subjects for $form",
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(subjects) { subject ->
                    SubjectCard(subject = subject, onSubjectClick = onSubjectClick)
                }
            }
        } else {
            Text("No subjects found for your selected form.")
        }
    }
}
