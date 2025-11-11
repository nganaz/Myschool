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
        Color(0xFF1ABC9C),  // Turquoise
        Color(0xFF8395A7), // Wild Blue Yonder
        Color(0xFF27AE60), // Nephritis
        Color(0xFFD35400), // Pumpkin
        Color(0xFFC0392B), // Pomegranate
        Color(0xFF7F8C8D)  // Asbestos
    )

    // 2. A map where the key is the grade level and the value is the list of subjects for that grade
    private val subjectsByGrade = mapOf(
        1 to listOf(
            Subject(id = "agric1", name = "Agriculture", icon = "🧑‍🌾", progress = 30, color = subjectColors[8]),
            Subject(id = "bio1", name = "Biology", icon = "🔬", progress = 55, color = subjectColors[7]),
            Subject(id = "chem1", name = "Chemistry", icon = "🧪", progress = 40, color = subjectColors[4]),
            Subject(id = "cs1", name = "Computer Studies", icon = "💻", progress = 45, color = subjectColors[6]),
            Subject(id = "eng1", name = "English", icon = "📖", progress = 75, color = subjectColors[0]),
            Subject(id = "hist1", name = "History", icon = "🏛️", progress = 70, color = subjectColors[5]),
            Subject(id = "math1", name = "Mathematics", icon = "🧮", progress = 50, color = subjectColors[1]),
            Subject(id = "phy1", name = "Physics", icon = "⚛️", progress = 60, color = subjectColors[2]),
            Subject(id = "ss1", name = "Social Studies", icon = "🌍", progress = 65, color = subjectColors[3])
        ),
        2 to listOf(
            Subject(id = "agric2", name = "Agriculture", icon = "🧑‍🌾", progress = 30, color = subjectColors[8]),
            Subject(id = "bio2", name = "Biology", icon = "🔬", progress = 60, color = subjectColors[7]),
            Subject(id = "chem2", name = "Chemistry", icon = "🧪", progress = 40, color = subjectColors[4]),
            Subject(id = "cs2", name = "Computer Studies", icon = "💻", progress = 55, color = subjectColors[6]),
            Subject(id = "eng2", name = "English", icon = "📖", progress = 80, color = subjectColors[0]),
            Subject(id = "hist2", name = "History", icon = "🏛️", progress = 75, color = subjectColors[5]),
            Subject(id = "math2", name = "Mathematics", icon = "🧮", progress = 65, color = subjectColors[1]),
            Subject(id = "phy2", name = "Physics", icon = "⚛️", progress = 58, color = subjectColors[2]),
            Subject(id = "ss2", name = "Social Studies", icon = "🌍", progress = 70, color = subjectColors[3])
        ),
        3 to listOf(
            Subject(id = "agric3", name = "Agriculture", icon = "🧑‍🌾", progress = 30, color = subjectColors[8]),
            Subject(id = "bio3", name = "Biology", icon = "🔬", progress = 75, color = subjectColors[7]),
            Subject(id = "chem3", name = "Chemistry", icon = "🧪", progress = 40, color = subjectColors[4]),
            Subject(id = "cs3", name = "Computer Studies", icon = "💻", progress = 65, color = subjectColors[6]),
            Subject(id = "eng3", name = "English", icon = "📖", progress = 85, color = subjectColors[0]),
            Subject(id = "hist3", name = "History", icon = "🏛️", progress = 80, color = subjectColors[5]),
            Subject(id = "math3", name = "Mathematics", icon = "📐", progress = 70, color = subjectColors[1]),
            Subject(id = "phy3", name = "Physics", icon = "⚛️", progress = 72, color = subjectColors[2]),
            Subject(id = "ss3", name = "Social Studies", icon = "🌍", progress = 78, color = subjectColors[3])
        ),
        4 to listOf(
            Subject(id = "agric4", name = "Agriculture", icon = "🧑‍🌾", progress = 30, color = subjectColors[8]),
            Subject(id = "bio4", name = "Biology", icon = "🔬", progress = 70, color = subjectColors[7]),
            Subject(id = "chem4", name = "Chemistry", icon = "🧪", progress = 40, color = subjectColors[4]),
            Subject(id = "cs4", name = "Computer Studies", icon = "💻", progress = 75, color = subjectColors[6]),
            Subject(id = "eng4", name = "English", icon = "📖", progress = 90, color = subjectColors[0]),
            Subject(id = "hist4", name = "History", icon = "🏛️", progress = 85, color = subjectColors[5]),
            Subject(id = "math4", name = "Advanced Mathematics", icon = "📐", progress = 80, color = subjectColors[1]),
            Subject(id = "phy4", name = "Physics", icon = "⚛️", progress = 88, color = subjectColors[2]),
            Subject(id = "ss4", name = "Social Studies", icon = "🌍", progress = 82, color = subjectColors[3])
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
