package com.example.myschool.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myschool.data.DataSource
import com.example.myschool.data.UserDataRepository

// A helper function to safely convert the form string to an integer
private fun getGradeLevelFromForm(form: String?): Int? {
    if (form == null) return null
    // This will handle strings like "Form 1" and extract the number
    return form.filter { it.isDigit() }.toIntOrNull()
}

@Composable
fun SubjectDetailsScreen(subjectId: String?) {

    val selectedForm = UserDataRepository.getSelectedForm()
    val gradeLevel = getGradeLevelFromForm(selectedForm)
    val subjects = if (gradeLevel != null) DataSource.getSubjectsForGrade(gradeLevel) else emptyList()
    val subject = subjects.find { it.id == subjectId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (subject != null) {
            Text(text = subject.icon, style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = subject.name, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Your current progress: ${subject.progress}%", style = MaterialTheme.typography.bodyLarge)
        } else {
            Text("Subject not found.")
        }
    }
}
