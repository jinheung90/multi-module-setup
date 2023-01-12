package jinheung.project.gateway.auth.r2dbc.repository

import jinheung.project.gateway.auth.r2dbc.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, Long> {
}