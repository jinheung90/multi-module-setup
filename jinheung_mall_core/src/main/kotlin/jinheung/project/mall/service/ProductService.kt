package jinheung.project.mall.service

import jinheung.project.mall.dto.ProductDTO
import jinheung.project.mall.entity.Mall
import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import jinheung.project.mall.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductService(
    private val productRepository : ProductRepository
) {

    @Transactional(readOnly = true)
    fun findAllProductsByMall(mallId: Long, category: Category) : List<Product> {
        return productRepository.findAllByMallIdAndCategory(mallId,category)
    }

    @Transactional
    fun registerProduct(mall: Mall, price: BigDecimal, quantity: Long, name: String, category: Category): ProductDTO {
        val product = productRepository.save(
            Product.of(
                mall = mall,
                price = price,
                quantity = quantity,
                name = name,
                category = category
            )
        )
        return ProductDTO.fromEntity(product);
    }

}