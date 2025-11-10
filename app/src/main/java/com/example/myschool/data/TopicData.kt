package com.example.myschool.data

fun findTopicById(topicId: String?): Topic? {
    val allTopics = englishGrammarTopics +
                    englishLiteratureTopicsForm1 +
                    englishLiteratureTopicsForm2 +
                    englishLiteratureTopicsForm3 +
                    englishLiteratureTopicsForm4 +
                    computerStudiesTopicsForm1 +
                    computerStudiesTopicsForm2 +
                    computerStudiesTopicsForm3 +
                    computerStudiesTopicsForm4
    return allTopics.find { it.id == topicId }
}
