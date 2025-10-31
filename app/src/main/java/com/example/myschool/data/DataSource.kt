package com.example.myschool.data

import com.example.myschool.data.User
import com.example.myschool.data.Subject

object DataSource {

    // 1. Simulate the currently logged-in user
    val currentUser = User(id = "user123", name = "Atusweghe", gradeLevel = 9)

    // 2. A map where the key is the grade level and the value is the list of subjects for that grade
    private val subjectsByGrade = mapOf(
        1 to listOf(
            Subject(id = "eng1", name = "English", icon = "📖", progress = 75),
            Subject(id = "math1", name = "Mathematics", icon = "🧮", progress = 50),
            Subject(id = "sci1", name = "Basic Science", icon = "🔬", progress = 60)
        ),
        2 to listOf(
            Subject(id = "eng2", name = "English", icon = "📖", progress = 80),
            Subject(id = "math2", name = "Mathematics", icon = "🧮", progress = 65),
            Subject(id = "soc2", name = "Social Studies", icon = "🌍", progress = 70)
        ),
        3 to listOf(
            Subject(id = "eng3", name = "English", icon = "📖", progress = 85),
            Subject(id = "math3", name = "Mathematics", icon = "📐", progress = 70),
            Subject(id = "sci3", name = "Integrated Science", icon = "🧪", progress = 75)
        ),
        4 to listOf(
            Subject(id = "eng4", name = "English", icon = "📖", progress = 90),
            Subject(id = "math4", name = "Advanced Mathematics", icon = "📐", progress = 80),
            Subject(id = "hist4", name = "History", icon = "🏛️", progress = 85)
        ),
        9 to listOf(
            // Use our Subject class, not the one from Google ML Kit
            Subject(id = "m9", name = "Computer Studies", icon = "🧮", progress = 65),
            Subject(id = "s9", name = "Biology", icon = "🔬", progress = 40),
            Subject(id = "h9", name = "History", icon = "🌍", progress = 80)
        ),
        10 to listOf(
            Subject(id = "m10", name = "Mathematics", icon = "📐", progress = 25),
            Subject(id = "s10", name = "Chemistry", icon = "🧪", progress = 50),
            Subject(id = "h10", name = "Geography", icon = "🏛️", progress = 95)
        )
        // We could add more grades here
    )

    // 3. A function to get the subjects for a specific grade
    fun getSubjectsForGrade(gradeLevel: Int): List<Subject> {
        // Return the list or an empty list if the grade isn't found
        return subjectsByGrade[gradeLevel] ?: emptyList()
    }
}
