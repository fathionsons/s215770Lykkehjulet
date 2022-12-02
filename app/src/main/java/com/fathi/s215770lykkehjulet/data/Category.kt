package com.fathi.s215770lykkehjulet.data

data class Category(
    val name: String,
    val words: List<String>
) {
    companion object {
        fun getCategories(): List<Category> {
            val categories: MutableList<Category> = emptyList<Category>().toMutableList()
            categories.add(
                Category(
                    "Cars",
                    listOf("seat", "audi", "alpina", "mercedes", "bwm", "volkswagen","nissan","toyota","volvo")
                )
            )
            categories.add(
                Category(
                    name = "Countries",
                    listOf("australia", "denmark", "austria", "belarus", "belgium", "brazil","kurdistan","germany","sweden")
                )
            )
            categories.add(
                Category(
                    "Games",
                    listOf("rust", "fifa", "apex", "pubg", "control", "battlefield","callofduty","ark")
                )
            )
            categories.add(
                Category(
                    "universities of Denmark",
                    listOf("dtu", "sdk", "ku")
                )
            )
            return categories
        }
    }
}
