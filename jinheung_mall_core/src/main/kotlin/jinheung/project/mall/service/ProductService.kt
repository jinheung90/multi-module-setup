package jinheung.project.mall.service

import jinheung.project.mall.dto.ProductDTO
import jinheung.project.mall.entity.Mall
import jinheung.project.mall.entity.Product

import jinheung.project.mall.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductService(
    private val productRepository : ProductRepository
) {

    @Transactional(readOnly = true)
    fun findAllProductsByMall(mallId: Long) : List<Product> {
        return productRepository.findAllByMallId(mallId)
    }

    @Transactional
    fun registerProduct(mall: Mall, price: BigDecimal, quantity: Long, name: String): ProductDTO {
        val product = productRepository.save(
            Product.of(
                mall = mall,
                price = price,
                quantity = quantity,
                name = name
            )
        )
        return ProductDTO.fromEntity(product)
    }

    @Transactional
    fun checkPriceAndStock() {

    }


    @Transactional(readOnly = true)
    fun findAllByIds(ids : List<Long>) : List<Product> {
        return productRepository.findAllById(ids)
    }
}