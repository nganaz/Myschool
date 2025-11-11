package com.example.myschool.data

val computerStudiesTopicsForm1 = listOf(
    Topic(id = "cs_f1_1", name = "Introduction to Computers", content = """
        1. What is a Computer?
        2. History of Computers.
        3. Types of Computers.
        4. Parts of a Computer (Hardware and Software).
        """.trimIndent()),
    Topic(id = "cs_f1_2", name = "Computer Hardware", content = """
        1. Input Devices (e.g., keyboard, mouse).
        2. Output Devices (e.g., monitor, printer).
        3. The Central Processing Unit (CPU).
        4. Memory (RAM and ROM).
        5. Storage Devices (e.g., hard drive, USB flash drive).
        """.trimIndent()),
    Topic(id = "cs_f1_3", name = "Computer Software", content = """
        1. System Software (e.g., Operating Systems like Windows, macOS, Linux).
        2. Application Software (e.g., Word processors, Spreadsheets).
        3. Utility Software (e.g., antivirus, disk cleanup).
        """.trimIndent())
)

val computerStudiesTopicsForm2 = listOf(
    Topic(id = "cs_f2_1", name = "The Operating System", content = """
        1. Functions of an Operating System.
        2. The User Interface (GUI and Command Line).
        3. File Management.
        """.trimIndent()),
    Topic(id = "cs_f2_2", name = "Word Processing", content = """
        1. Introduction to Word Processing.
        2. Basic Formatting.
        3. Working with Tables.
        4. Mail Merge.
        """.trimIndent()),
    Topic(id = "cs_f2_3", name = "Spreadsheets", content = """
        1. Introduction to Spreadsheets.
        2. Formulas and Functions.
        3. Creating Charts.
        4. Data Sorting and Filtering.
        """.trimIndent())
)

val computerStudiesTopicsForm3 = listOf(
    Topic(id = "cs_f3_1", name = "Databases", content = """
        1. Introduction to Databases.
        2. Database Objects (Tables, Queries, Forms, Reports).
        3. Data Types.
        4. Primary Keys and Relationships.
        """.trimIndent()),
    Topic(id = "cs_f3_2", name = "Desktop Publishing", content = """
        1. Introduction to Desktop Publishing (DTP).
        2. Principles of Design.
        3. Working with Text and Graphics.
        """.trimIndent()),
    Topic(id = "cs_f3_3", name = "Computer Networks", content = """
        1. Types of Networks (LAN, WAN).
        2. Network Topologies.
        3. The Internet and the World Wide Web.
        """.trimIndent())
)

val computerStudiesTopicsForm4 = listOf(
    Topic(id = "cs_f4_1", name = "Programming Fundamentals", content = """
        1. Introduction to Programming.
        2. Program Development Life Cycle.
        3. Algorithms and Flowcharts.
        4. Introduction to a Programming Language (e.g., Python, BASIC).
        """.trimIndent()),
    Topic(id = "cs_f4_2", name = "Web Design", content = """
        1. Introduction to HTML.
        2. Basic HTML Tags.
        3. Introduction to CSS.
        4. Publishing a Website.
        """.trimIndent()),
    Topic(id = "cs_f4_3", name = "Computer Security and Ethics", content = """
        1. Threats to Computer Systems (e.g., viruses, malware, hacking).
        2. Security Measures.
        3. Computer Ethics and Copyright.
        """.trimIndent())
)

fun getComputerStudiesTopicsForForm(form: String?): List<Topic> {
    return when (form) {
        "Form 1" -> computerStudiesTopicsForm1
        "Form 2" -> computerStudiesTopicsForm2
        "Form 3" -> computerStudiesTopicsForm3
        "Form 4" -> computerStudiesTopicsForm4
        else -> emptyList()
    }
}

fun getComputerStudiesTopic(topicId: String?): Topic? {
    return computerStudiesTopicsForm1.find { it.id == topicId }
        ?: computerStudiesTopicsForm2.find { it.id == topicId }
        ?: computerStudiesTopicsForm3.find { it.id == topicId }
        ?: computerStudiesTopicsForm4.find { it.id == topicId }
}
