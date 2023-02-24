package jinheung.project.mall.service

import jinheung.project.mall.dto.MallDTO
import jinheung.project.mall.entity.Mall
import jinheung.project.mall.entity.MallHasUser
import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import jinheung.project.mall.repository.MallHasUserRepository
import jinheung.project.mall.repository.MallRepository
import jinheung.project.mall.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MallService(
    private val mallRepository: MallRepository,
    private val mallHasUserRepository: MallHasUserRepository,
    private val productRepository : ProductRepository
) {

    @Transactional
    fun registerMall(userId : Long, name: String) : MallDTO {
        val mall = this.saveMall(name = name)
        val mallHasUser = this.saveMallHasUser(mall, userId);
        return MallDTO.toDto(mall)
    }

    @Transactional
    fun saveMall(name: String) : Mall {
        return mallRepository.save(Mall.of(name))
    }

    @Transactional
    fun saveMallHasUser(mall: Mall, userId: Long) : MallHasUser {
        return mallHasUserRepository.save(MallHasUser.of(mall,userId))
    }

    @Transactional(readOnly = true)
    fun findAllProductsByMall(mallId: Long, category: Category) : List<Product> {
        return productRepository.findAllByMallIdAndCategory(mallId,category)
    }
}