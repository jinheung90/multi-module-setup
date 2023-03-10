package jinheung.project.mall.repository

import jinheung.project.mall.entity.Mall
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository

interface MallRepository : JpaRepository<Mall, Long> {
}