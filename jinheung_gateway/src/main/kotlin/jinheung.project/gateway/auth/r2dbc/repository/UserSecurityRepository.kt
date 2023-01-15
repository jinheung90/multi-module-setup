package jinheung.project.gateway.auth.r2dbc.repository;


import jinheung.project.gateway.auth.r2dbc.entity.User
import jinheung.project.gateway.auth.r2dbc.entity.UserSecurity

import kotlinx.coroutines.flow.Flow


import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository



interface UserSecurityRepository : CoroutineCrudRepository<UserSecurity, Long> {
    fun findByEmail(email : String) : Flow<UserSecurity>
}