package jinheung.project.auth.repository



import jinheung.project.auth.entity.UserSecurity
import kotlinx.coroutines.flow.Flow


import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface UserSecurityRepository : CoroutineCrudRepository<UserSecurity, Long> {
    fun findByEmail(email : String) : Flow<UserSecurity>
}