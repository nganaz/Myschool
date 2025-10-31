package com.example.myschool.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myschool.data.DataSource
import com.example.myschool.data.UserDataRepository

// A helper function to safely convert the form string to an integer
private fun getGradeLevelFromForm(form: String?): Int? {
    if (form == null) return null
    // This will handle strings like "Form 1" and extract the number
    return form.filter { it.isDigit() }.toIntOrNull()
}

@Composable
fun HomeScreen(navController: NavController) {

    val selectedForm = UserDataRepository.getSelectedForm()
    val gradeLevel = getGradeLevelFromForm(selectedForm)
    val subjects = if (gradeLevel != null) DataSource.getSubjectsForGrade(gradeLevel) else emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Welcome to ${selectedForm ?: "MySchool"}!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (subjects.isNotEmpty()) {
            Text(
                text = "Your Subjects",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Replace the old list with our new card-based list
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(subjects) { subject ->
                    // Use our new SubjectCard composable here!
                    SubjectCard(subject = subject)
                }
            }
        } else {
            Text("No subjects found for your selected form.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
