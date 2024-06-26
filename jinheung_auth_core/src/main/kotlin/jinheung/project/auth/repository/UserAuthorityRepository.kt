package jinheung.project.auth.repository

import jinheung.project.auth.entity.UserAuthority
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserAuthorityRepository : CoroutineCrudRepository<UserAuthority, Long> {

}