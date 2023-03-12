package jinheung.project.mall.enums

import jinheung.project.error.enums.GlobalErrorCode
import jinheung.project.error.exception.CustomBadRequest

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
            throw CustomBadRequest(GlobalErrorCode.BAD_REQUEST, "not exists category");

        }
    }
}