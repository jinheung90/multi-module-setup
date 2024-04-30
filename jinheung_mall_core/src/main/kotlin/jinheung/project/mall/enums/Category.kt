package jinheung.project.mall.enums

import jinheung.project.error.enums.GlobalErrorCode
import jinheung.project.error.exception.CustomBadRequest

enum class ProductCategoryType {
    FOOD,
    BOOK,
    COMPUTER;

    companion object {
        fun findCategory(category: String) : ProductCategoryType {
            for (c in ProductCategoryType.values()) {
                if(c.name == category) {
                    return c
                }
            }
            throw CustomBadRequest(GlobalErrorCode.BAD_REQUEST, "not exists category");
        }
    }
}