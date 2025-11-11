package com.example.myschool.data

import com.example.myschool.data.biologyTopicsForm1
import com.example.myschool.data.biologyTopicsForm2
import com.example.myschool.data.biologyTopicsForm3
import com.example.myschool.data.biologyTopicsForm4
import com.example.myschool.data.computerStudiesTopicsForm1
import com.example.myschool.data.computerStudiesTopicsForm2
import com.example.myschool.data.computerStudiesTopicsForm3
import com.example.myschool.data.computerStudiesTopicsForm4
import com.example.myschool.data.englishGrammarTopics
import com.example.myschool.data.englishLiteratureTopicsForm1
import com.example.myschool.data.englishLiteratureTopicsForm2
import com.example.myschool.data.englishLiteratureTopicsForm3
import com.example.myschool.data.englishLiteratureTopicsForm4
import com.example.myschool.data.historyTopicsForm1
import com.example.myschool.data.historyTopicsForm2
import com.example.myschool.data.historyTopicsForm3
import com.example.myschool.data.historyTopicsForm4
import com.example.myschool.data.mathematicsTopicsForm1
import com.example.myschool.data.mathematicsTopicsForm2
import com.example.myschool.data.mathematicsTopicsForm3
import com.example.myschool.data.mathematicsTopicsForm4
import com.example.myschool.data.physicsTopicsForm1
import com.example.myschool.data.physicsTopicsForm2
import com.example.myschool.data.physicsTopicsForm3
import com.example.myschool.data.physicsTopicsForm4
import com.example.myschool.data.socialStudiesTopicsForm1
import com.example.myschool.data.socialStudiesTopicsForm2
import com.example.myschool.data.socialStudiesTopicsForm3
import com.example.myschool.data.socialStudiesTopicsForm4

fun findTopicById(topicId: String?): Topic? {
    val allTopics: List<Topic> = englishGrammarTopics +
            englishLiteratureTopicsForm1 +
            englishLiteratureTopicsForm2 +
            englishLiteratureTopicsForm3 +
            englishLiteratureTopicsForm4 +
            computerStudiesTopicsForm1 +
            computerStudiesTopicsForm2 +
            computerStudiesTopicsForm3 +
            computerStudiesTopicsForm4 +
            mathematicsTopicsForm1 +
            mathematicsTopicsForm2 +
            mathematicsTopicsForm3 +
            mathematicsTopicsForm4 +
            physicsTopicsForm1 +
            physicsTopicsForm2 +
            physicsTopicsForm3 +
            physicsTopicsForm4 +
            socialStudiesTopicsForm1 +
            socialStudiesTopicsForm2 +
            socialStudiesTopicsForm3 +
            socialStudiesTopicsForm4 +
            historyTopicsForm1 +
            historyTopicsForm2 +
            historyTopicsForm3 +
            historyTopicsForm4 +
            biologyTopicsForm1 +
            biologyTopicsForm2 +
            biologyTopicsForm3 +
            biologyTopicsForm4
    return allTopics.find { it.id == topicId }
}
