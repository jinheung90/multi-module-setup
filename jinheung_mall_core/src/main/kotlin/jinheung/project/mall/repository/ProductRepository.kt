package jinheung.project.mall.repository

import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByMallIdAndCategory(mall_id: Long, category: Category) : List<Product>
}