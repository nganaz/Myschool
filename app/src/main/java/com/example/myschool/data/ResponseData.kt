package com.example.myschool.data

object ResponseData {
    val responses = listOf(
        Response(
            id = 1,
            questionId = 1,
            author = "Emily White",
            authorImageUrl = "",
            date = "14/02/2024 - 13:00",
            response = "The formula for the area of a circle is A = πr², where r is the radius of the circle.",
            likes = 25
        ),
        Response(
            id = 2,
            questionId = 2,
            author = "David Green",
            authorImageUrl = "",
            date = "10/02/2024 - 10:00",
            response = "The three main states of matter are solid, liquid, and gas.",
            likes = 18
        ),
        Response(
            id = 3,
            questionId = 3,
            author = "Sarah Brown",
            authorImageUrl = "",
            date = "08/02/2024 - 18:00",
            response = "George Washington was the first president of the United States.",
            likes = 32
        )
    )
}
