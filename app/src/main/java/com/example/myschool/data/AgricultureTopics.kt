package com.example.myschool.data

val agricultureTopicsForm1 = listOf(
    Topic(id = "agric_f1_1", name = "Introduction to Agriculture", content = """
        1. Definition of Agriculture: The science and art of cultivating plants and rearing animals.
        2. Importance of Agriculture in Malawi: Contribution to GDP, employment, food security, and foreign exchange.
        3. Branches of Agriculture: Crop production, animal production, soil science, agricultural engineering, and agricultural economics.
        4. Challenges Facing Agriculture in Malawi: Pests and diseases, unreliable rainfall, poor soil fertility, and limited access to markets.
        """.trimIndent()),
    Topic(id = "agric_f1_2", name = "Soil and its Importance", content = """
        1. What is Soil?: The upper layer of earth in which plants grow.
        2. Soil Formation: The process of weathering of rocks and decomposition of organic matter.
        3. Soil Composition: Minerals, organic matter, water, and air.
        4. Soil Profile: The different layers of soil (horizons).
        5. Types of Soil in Malawi: Sandy, clay, and loam soils.
        """.trimIndent()),
    Topic(id = "agric_f1_3", name = "Crop Production Basics", content = """
        1. Land Preparation: Clearing, ploughing, and harrowing.
        2. Planting: Selecting seeds, timing, spacing, and depth.
        3. Weeding: Importance and methods of weed control.
        4. Harvesting: Proper timing and methods.
        5. Common Crops in Malawi: Maize, tobacco, groundnuts, cassava, and tea.
        """.trimIndent())
)

val agricultureTopicsForm2 = listOf(
    Topic(id = "agric_f2_1", name = "Soil Fertility and Management", content = """
        1. Plant Nutrients: Macronutrients (N, P, K) and micronutrients.
        2. Soil Fertility: The ability of soil to supply nutrients for plant growth.
        3. Manures and Fertilizers: Organic (compost, animal manure) and inorganic (chemical) fertilizers.
        4. Soil and Water Conservation: Methods like contour ridging, crop rotation, and agroforestry to prevent soil erosion.
        5. Irrigation: The artificial application of water to land to assist in the production of crops.
        """.trimIndent()),
    Topic(id = "agric_f2_2", name = "Pest and Disease Management", content = """
        1. Common Pests in Malawi: Maize stalk borer, armyworms, and aphids.
        2. Common Diseases in Malawi: Maize streak virus, cassava mosaic disease.
        3. Methods of Pest and Disease Control: Cultural, biological, chemical, and integrated pest management (IPM).
        4. Safe Use of Pesticides: Protective clothing, proper mixing, and disposal.
        """.trimIndent()),
    Topic(id = "agric_f2_3", name = "Animal Production: Poultry and Livestock", content = """
        1. Importance of Livestock in Malawi: Food, income, manure, and draught power.
        2. Types of Livestock: Cattle, goats, pigs, and poultry (chickens, ducks).
        3. Housing and Feeding: Proper housing and balanced diets for livestock.
        4. Common Livestock Diseases: Newcastle disease in poultry, foot-and-mouth disease in cattle.
        5. Animal Breeding: Basic concepts of selection and breeding for better traits.
        """.trimIndent())
)

val agricultureTopicsForm3 = listOf(
    Topic(id = "agric_f3_1", name = "Farm Tools and Machinery", content = """
        1. Basic Farm Tools: Hand hoe, panga knife, slasher, and rake.
        2. Animal-drawn Implements: Ploughs, ridgers, and carts.
        3. Tractor-drawn Machinery: Ploughs, harrows, planters, and harvesters.
        4. Maintenance of Tools and Machinery: Cleaning, sharpening, and proper storage.
        """.trimIndent()),
    Topic(id = "agric_f3_2", name = "Crop Improvement and Biotechnology", content = """
        1. Principles of Crop Improvement: Selection and breeding.
        2. Hybrid Seed Production: The process and advantages of using hybrid seeds.
        3. Genetically Modified (GM) Crops: Concepts, benefits, and controversies.
        4. Tissue Culture: A method of propagating plants in a laboratory.
        """.trimIndent()),
    Topic(id = "agric_f3_3", name = "Forage and Pasture Management", content = """
        1. Importance of Forage Crops: Feeding livestock.
        2. Common Forage Crops in Malawi: Napier grass, Rhodes grass, and legumes like leucena.
        3. Pasture Management: Rotational grazing, weed control, and maintaining pasture quality.
        4. Fodder Conservation: Methods like making hay and silage to preserve feed for the dry season.
        """.trimIndent())
)

val agricultureTopicsForm4 = listOf(
    Topic(id = "agric_f4_1", name = "Agricultural Economics and Farm Management", content = """
        1. Basic Economic Concepts: Scarcity, choice, and opportunity cost.
        2. Factors of Production: Land, labor, capital, and management.
        3. Farm Records and Accounts: Importance and types of records (e.g., production, financial).
        4. Farm Budgeting: Planning for income and expenditure.
        5. Marketing of Agricultural Produce: Channels, challenges, and the role of marketing boards like ADMARC.
        """.trimIndent()),
    Topic(id = "agric_f4_2", name = "Agroforestry and Climate Change", content = """
        1. What is Agroforestry?: Integrating trees and shrubs with crops and/or livestock.
        2. Benefits of Agroforestry: Improved soil fertility, reduced soil erosion, provision of firewood and fodder.
        3. Climate Change and its Impact on Agriculture: Changing rainfall patterns, increased temperatures, and extreme weather events.
        4. Climate-Smart Agriculture: Practices that increase productivity, enhance resilience, and reduce greenhouse gas emissions.
        """.trimIndent()),
    Topic(id = "agric_f4_3", name = "Aquaculture", content = """
        1. Introduction to Aquaculture: The farming of fish and other aquatic organisms.
        2. Importance of Aquaculture in Malawi: Source of protein, income, and employment.
        3. Fish Pond Construction and Management: Site selection, construction, stocking, feeding, and harvesting.
        4. Common Fish Species Farmed in Malawi: Tilapia (Chambo), and catfish (Mlamba).
        """.trimIndent())
)

fun getAgricultureTopicsForForm(form: String?): List<Topic> {
    return when (form) {
        "Form 1" -> agricultureTopicsForm1
        "Form 2" -> agricultureTopicsForm2
        "Form 3" -> agricultureTopicsForm3
        "Form 4" -> agricultureTopicsForm4
        else -> emptyList()
    }
}

fun getAgricultureTopic(topicId: String?): Topic? {
    return agricultureTopicsForm1.find { it.id == topicId }
        ?: agricultureTopicsForm2.find { it.id == topicId }
        ?: agricultureTopicsForm3.find { it.id == topicId }
        ?: agricultureTopicsForm4.find { it.id == topicId }
}
