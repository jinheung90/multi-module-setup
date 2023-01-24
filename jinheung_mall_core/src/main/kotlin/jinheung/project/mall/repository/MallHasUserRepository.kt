package jinheung.project.mall.repository

import jinheung.project.mall.entity.MallHasUser
import org.springframework.data.jpa.repository.JpaRepository


interface MallHasUserRepository : JpaRepository<MallHasUser, Long> {
}