package jinheung.project.mall.repository

import jinheung.project.mall.entity.Product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByMallId(mallId: Long) : List<Product>
}