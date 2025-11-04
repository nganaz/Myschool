package com.example.myschool.data

import androidx.compose.ui.graphics.Color

object DataSource {

    // 1. Simulate the currently logged-in user
    val currentUser = User(id = "user123", name = "Atusweghe", gradeLevel = 9)

    private val subjectColors = listOf(
        Color(0xFF2ECC71), // Emerald
        Color(0xFF3498DB), // Peter River
        Color(0xFF9B59B6), // Amethyst
        Color(0xFFF1C40F), // Sun Flower
        Color(0xFFE74C3C), // Alizarin
        Color(0xFF1ABC9C)  // Turquoise
    )

    private val englishTopics = listOf(
        Topic(id = "grammar", name = "Common Grammar", content = "This is the content for common grammar."),
        Topic(id = "literature", name = "Literature", content = "This is the content for literature.")
    )

    // 2. A map where the key is the grade level and the value is the list of subjects for that grade
    private val subjectsByGrade = mapOf(
        1 to listOf(
            Subject(id = "eng1", name = "English", icon = "📖", progress = 75, color = subjectColors[0], topics = englishTopics),
            Subject(id = "math1", name = "Mathematics", icon = "🧮", progress = 50, color = subjectColors[1]),
            Subject(id = "sci1", name = "Physics", icon = "🔬", progress = 60, color = subjectColors[2])
        ),
        2 to listOf(
            Subject(id = "eng2", name = "English", icon = "📖", progress = 80, color = subjectColors[0], topics = englishTopics),
            Subject(id = "math2", name = "Mathematics", icon = "🧮", progress = 65, color = subjectColors[1]),
            Subject(id = "soc2", name = "Social Studies", icon = "🌍", progress = 70, color = subjectColors[3])
        ),
        3 to listOf(
            Subject(id = "eng3", name = "English", icon = "📖", progress = 85, color = subjectColors[0], topics = englishTopics),
            Subject(id = "math3", name = "Mathematics", icon = "📐", progress = 70, color = subjectColors[1]),
            Subject(id = "sci3", name = "Chemistry", icon = "🧪", progress = 75, color = subjectColors[4])
        ),
        4 to listOf(
            Subject(id = "eng4", name = "English", icon = "📖", progress = 90, color = subjectColors[0], topics = englishTopics),
            Subject(id = "math4", name = "Advanced Mathematics", icon = "📐", progress = 80, color = subjectColors[1]),
            Subject(id = "hist4", name = "History", icon = "🏛️", progress = 85, color = subjectColors[5])
        )
    )

    // 3. A function to get the subjects for a specific grade
    fun getSubjectsForGrade(gradeLevel: Int): List<Subject> {
        // Return the list or an empty list if the grade isn't found
        return subjectsByGrade[gradeLevel] ?: emptyList()
    }

    fun getSubjectById(subjectId: String): Subject? {
        return subjectsByGrade.values.flatten().find { it.id == subjectId }
    }

    fun getAllForms(): List<String> {
        return subjectsByGrade.keys.map { "Form $it" }
    }

    val exploreTopics = listOf(
        "Marathon Running",
        "Natural",
        "Read Fast Learn More",
        "Creative"
    )
}
