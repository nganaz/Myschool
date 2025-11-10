package com.example.myschool.data

object QuestionData {
    val questions = listOf(
        Question(
            id = 1,
            tags = listOf("#Homework", "#Math"),
            subject = "Mathematics",
            question = "What is the formula for the area of a circle?",
            author = "John Doe",
            authorImageUrl = "", // Add a placeholder image url
            date = "14/02/2024 - 12:30",
            comments = 15,
            likes = 50
        ),
        Question(
            id = 2,
            tags = listOf("#Exam", "#Science"),
            subject = "Science",
            question = "What are the states of matter?",
            author = "Jane Smith",
            authorImageUrl = "", // Add a placeholder image url
            date = "10/02/2024 - 09:24",
            comments = 10,
            likes = 32
        ),
        Question(
            id = 3,
            tags = listOf("#Project", "#History"),
            subject = "History",
            question = "Who was the first president of the United States?",
            author = "Peter Jones",
            authorImageUrl = "", // Add a placeholder image url
            date = "08/02/2024 - 17:00",
            comments = 20,
            likes = 75
        )
    )
}
