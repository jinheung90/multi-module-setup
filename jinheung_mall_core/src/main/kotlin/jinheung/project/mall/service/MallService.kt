package jinheung.project.mall.service

import jinheung.project.error.enums.GlobalErrorCode
import jinheung.project.error.exception.CustomBadRequest
import jinheung.project.mall.dto.MallDTO
import jinheung.project.mall.entity.Mall
import jinheung.project.mall.entity.MallHasUser

import jinheung.project.mall.repository.MallHasUserRepository
import jinheung.project.mall.repository.MallRepository
import jinheung.project.mall.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MallService(
    private val mallRepository: MallRepository,
    private val mallHasUserRepository: MallHasUserRepository
) {
    @Transactional
    fun registerMall(userId : Long, name: String) : MallDTO {
        val mall = this.saveMall(name = name)
        val mallHasUser = this.saveMallHasUser(mall, userId);
        return MallDTO.toDto(mall, mall.mallHasUsers)
    }

    @Transactional
    fun saveMall(name: String) : Mall {
        return mallRepository.save(Mall(name))
    }

    @Transactional
    fun saveMallHasUser(mall: Mall, userId: Long) : MallHasUser {
        return mallHasUserRepository.save(MallHasUser(userId, mall))
    }
}