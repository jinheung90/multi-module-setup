package jinheung.project.mall.enums

enum class Category {
    FOOD,
    BOOK,
    COMPUTER;
    companion object {
        fun findCategory(category: String) : Category {
            for (c in Category.values()) {
                if(c.name == category) {
                    return c
                }
            }
            throw RuntimeException()
        }
    }
}