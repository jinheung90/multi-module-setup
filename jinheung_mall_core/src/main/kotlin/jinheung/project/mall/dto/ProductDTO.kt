package jinheung.project.mall.dto

import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import java.math.BigDecimal

data class ProductDTO(
    val id : Long,
    val price : BigDecimal,
    val quantity : Long = 0,
    val name: String,
    val category: String,
    val mallId : Long
) {
    companion object {
        fun fromEntity(product: Product) : ProductDTO {
            return ProductDTO(
                mallId = product.mall.id,
                price = product.price,
                name = product.name,
                category = product.category.toString(),
                quantity = product.quantity,
                id = product.id)
        }
    }
}