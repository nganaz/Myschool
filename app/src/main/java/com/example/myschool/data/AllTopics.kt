package com.example.myschool.data

fun searchAllTopics(query: String): List<Topic> {
    val allTopics = mutableListOf<Topic>()
    allTopics.addAll(computerStudiesTopicsForm1)
    allTopics.addAll(computerStudiesTopicsForm2)
    allTopics.addAll(computerStudiesTopicsForm3)
    allTopics.addAll(computerStudiesTopicsForm4)
    allTopics.addAll(agricultureTopicsForm1)
    allTopics.addAll(agricultureTopicsForm2)
    allTopics.addAll(agricultureTopicsForm3)
    allTopics.addAll(agricultureTopicsForm4)
    allTopics.addAll(biologyTopicsForm1)
    allTopics.addAll(biologyTopicsForm2)
    allTopics.addAll(biologyTopicsForm3)
    allTopics.addAll(biologyTopicsForm4)
    allTopics.addAll(chemistryTopicsForm1)
    allTopics.addAll(chemistryTopicsForm2)
    allTopics.addAll(chemistryTopicsForm3)
    allTopics.addAll(chemistryTopicsForm4)
    allTopics.addAll(englishGrammarTopics)
    allTopics.addAll(englishLiteratureTopicsForm1)
    allTopics.addAll(englishLiteratureTopicsForm2)
    allTopics.addAll(englishLiteratureTopicsForm3)
    allTopics.addAll(englishLiteratureTopicsForm4)
    allTopics.addAll(historyTopicsForm1)
    allTopics.addAll(historyTopicsForm2)
    allTopics.addAll(historyTopicsForm3)
    allTopics.addAll(historyTopicsForm4)
    allTopics.addAll(mathematicsTopicsForm1)
    allTopics.addAll(mathematicsTopicsForm2)
    allTopics.addAll(mathematicsTopicsForm3)
    allTopics.addAll(mathematicsTopicsForm4)
    allTopics.addAll(physicsTopicsForm1)
    allTopics.addAll(physicsTopicsForm2)
    allTopics.addAll(physicsTopicsForm3)
    allTopics.addAll(physicsTopicsForm4)
    allTopics.addAll(socialStudiesTopicsForm1)
    allTopics.addAll(socialStudiesTopicsForm2)
    allTopics.addAll(socialStudiesTopicsForm3)
    allTopics.addAll(socialStudiesTopicsForm4)

    if (query.isBlank()) {
        return emptyList()
    }

    return allTopics.filter {
        it.name.contains(query, ignoreCase = true) ||
        it.content.contains(query, ignoreCase = true)
    }
}
