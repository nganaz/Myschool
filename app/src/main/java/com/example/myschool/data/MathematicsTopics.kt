package com.example.myschool.data

val mathematicsTopicsForm1 = listOf(
    Topic(id = "math_f1_1", name = "Topic 1", content = "Content for topic 1"),
    Topic(id = "math_f1_2", name = "Topic 2", content = "Content for topic 2"),
)

val mathematicsTopicsForm2 = listOf(
    Topic(id = "math_f2_1", name = "Topic 1", content = "Content for topic 1"),
    Topic(id = "math_f2_2", name = "Topic 2", content = "Content for topic 2"),
)

val mathematicsTopicsForm3 = listOf(
    Topic(id = "math_f3_1", name = "Topic 1", content = "Content for topic 1"),
    Topic(id = "math_f3_2", name = "Topic 2", content = "Content for topic 2"),
)

val mathematicsTopicsForm4 = listOf(
    Topic(id = "math_f4_1", name = "Topic 1", content = "Content for topic 1"),
    Topic(id = "math_f4_2", name = "Topic 2", content = "Content for topic 2"),
)

fun getMathematicsTopicsForForm(form: String?): List<Topic> {
    return when (form) {
        "Form 1" -> mathematicsTopicsForm1
        "Form 2" -> mathematicsTopicsForm2
        "Form 3" -> mathematicsTopicsForm3
        "Form 4" -> mathematicsTopicsForm4
        else -> emptyList()
    }
}

fun getMathematicsTopic(topicId: String?): Topic? {
    return mathematicsTopicsForm1.find { it.id == topicId } 
        ?: mathematicsTopicsForm2.find { it.id == topicId } 
        ?: mathematicsTopicsForm3.find { it.id == topicId } 
        ?: mathematicsTopicsForm4.find { it.id == topicId }
}
