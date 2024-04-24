package jinheung.project.auth.repository

import jinheung.project.auth.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface UserRepository : CoroutineCrudRepository<User, Long> {

}