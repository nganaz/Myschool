package com.example.myschool.data

val mathematicsTopicsForm1 = listOf(
    Topic(id = "math_f1_1", name = "Numbers and Algebra", content = """
        1. Number Systems.
        2. Fractions, Decimals, and Percentages.
        3. Algebraic Expressions.
        4. Linear Equations.
        """.trimIndent()),
    Topic(id = "math_f1_2", name = "Geometry", content = """
        1. Angles and Lines.
        2. Triangles and Quadrilaterals.
        3. Circles.
        4. Area and Perimeter.
        """.trimIndent()),
    Topic(id = "math_f1_3", name = "Statistics", content = """
        1. Data Collection and Representation.
        2. Measures of Central Tendency (Mean, Median, Mode).
        """.trimIndent())
)

val mathematicsTopicsForm2 = listOf(
    Topic(id = "math_f2_1", name = "Algebra", content = """
        1. Simultaneous Equations.
        2. Quadratic Equations.
        3. Inequalities.
        4. Indices and Logarithms.
        """.trimIndent()),
    Topic(id = "math_f2_2", name = "Trigonometry", content = """
        1. Trigonometric Ratios (Sine, Cosine, Tangent).
        2. The Sine and Cosine Rules.
        3. Area of a Triangle.
        """.trimIndent()),
    Topic(id = "math_f2_3", name = "Vectors and Matrices", content = """
        1. Vectors.
        2. Matrices.
        3. Transformations.
        """.trimIndent())
)

val mathematicsTopicsForm3 = listOf(
    Topic(id = "math_f3_1", name = "Calculus", content = """
        1. Differentiation.
        2. Integration.
        3. Applications of Calculus.
        """.trimIndent()),
    Topic(id = "math_f3_2", name = "Probability", content = """
        1. Introduction to Probability.
        2. Mutually Exclusive Events.
        3. Independent Events.
        """.trimIndent()),
    Topic(id = "math_f3_3", name = "Linear Programming", content = """
        1. Forming Linear Inequalities.
        2. Graphical Solution.
        3. Optimization.
        """.trimIndent())
)

val mathematicsTopicsForm4 = listOf(
    Topic(id = "math_f4_1", name = "Advanced Algebra", content = """
        1. Polynomials.
        2. Series and Sequences.
        3. The Binomial Theorem.
        """.trimIndent()),
    Topic(id = "math_f4_2", name = "3D Geometry", content = """
        1. Lines and Planes in 3D.
        2. Vectors in 3D.
        """.trimIndent()),
    Topic(id = "math_f4_3", name = "Mechanics", content = """
        1. Kinematics.
        2. Dynamics.
        3. Statics.
        """.trimIndent())
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
