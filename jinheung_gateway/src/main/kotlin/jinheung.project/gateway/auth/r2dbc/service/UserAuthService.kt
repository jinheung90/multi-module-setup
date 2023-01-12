package jinheung.project.gateway.auth.r2dbc.service

import com.example.jinheunggateway.auth.dto.UserAuthDto
import com.example.jinheunggateway.auth.r2dbc.entity.User
import com.example.jinheunggateway.auth.r2dbc.entity.UserAuthority
import com.example.jinheunggateway.auth.r2dbc.entity.UserSecurity
import jinheung.project.gateway.auth.r2dbc.repository.AuthorityRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserAuthorityRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserSecurityRepository
import kotlinx.coroutines.flow.toList
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserAuthService(
        private val authorityRepository: AuthorityRepository,
        private val userAuthorityRepository: UserAuthorityRepository,
        private val userRepository: UserRepository,
        private val userSecurityRepository: UserSecurityRepository,
        private val passwordEncoder: PasswordEncoder
    ) {

    private final val commonUserAuthorities : List<String> = listOf("ROLE_USER")

    @Transactional
    suspend fun signup(email : String, password : String) : UserAuthDto {

        val user = userRepository.save(User())
        val authorities = authorityRepository.findAllByNameIn(commonUserAuthorities)

        userAuthorityRepository.saveAll(
                authorities.toList()
                    .map { a ->  UserAuthority.of(authorityId = a.id, userId = user.id) }
        )

        val userSecurity = userSecurityRepository
                .save(UserSecurity.of(user.id, email, passwordEncoder.encode(password)))
        return UserAuthDto.of(user.id, userSecurity, authorities.toList())
    }
}