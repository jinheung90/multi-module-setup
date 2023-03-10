package jinheung.project.mall.repository

import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByMallIdAndCategory(mallId: Long, category: Category) : List<Product>
}