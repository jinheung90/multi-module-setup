package jinheung.project.gateway.auth.r2dbc.repository

import com.example.jinheunggateway.auth.r2dbc.entity.UserAuthority
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserAuthorityRepository : CoroutineCrudRepository<UserAuthority, Long> {

}