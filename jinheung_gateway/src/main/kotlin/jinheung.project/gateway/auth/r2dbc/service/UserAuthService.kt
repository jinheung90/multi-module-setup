package jinheung.project.gateway.auth.r2dbc.service


import jinheung.project.gateway.auth.dto.LoginDto
import jinheung.project.gateway.auth.dto.UserAuthDto
import jinheung.project.gateway.auth.r2dbc.entity.User
import jinheung.project.gateway.auth.r2dbc.entity.UserAuthority
import jinheung.project.gateway.auth.r2dbc.entity.UserSecurity

import jinheung.project.gateway.auth.r2dbc.repository.AuthorityRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserAuthorityRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserSecurityRepository
import jinheung.project.gateway.jwt.TokenProvider
import kotlinx.coroutines.flow.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok


@Service
class UserAuthService(
        private val authorityRepository: AuthorityRepository,
        private val userAuthorityRepository: UserAuthorityRepository,
        private val userRepository: UserRepository,
        private val userSecurityRepository: UserSecurityRepository,
        private val passwordEncoder: PasswordEncoder,
        private val tokenProvider: TokenProvider
    ) {

    private final val commonUserAuthorities : List<String> = listOf("ROLE_USER")

    @Transactional
    suspend fun signup(email: String, password: String) : UserAuthDto {
        println("test")
        val user = userRepository.save(User.of())

        val authorities = authorityRepository.findAllByNameIn(commonUserAuthorities)
        print(authorities.count())

        val userAuthorities = authorities
            .map { a ->  UserAuthority.of(authorityId = a.id, userId = user.id) }
            .toList()

        userAuthorityRepository.saveAll(userAuthorities)

        val userSecurity = userSecurityRepository
                .save(UserSecurity.of(user.id, email, passwordEncoder.encode(password)))
        return UserAuthDto.of(userSecurity.userId, userSecurity, authorities.toList())
    }

    @Transactional
    suspend fun loginWithPassword(email: String, password: String) : HashMap<String,String> {
        val userSecurity = userSecurityRepository.findByEmail(email).last()
        this.verifyPassword(password, userSecurity.password)
        val userAuthorities = authorityRepository.findAllByUserId(userSecurity.userId)
        val accessToken = this.getAccessToken(userSecurity.userId, userAuthorities.map { ua -> ua.name }.toList())
        val response = HashMap<String,String>()
        response["access"] = accessToken
        return response
    }



    private fun verifyPassword(password: String, encodedPassword: String) {
        if(!passwordEncoder.matches(password, encodedPassword)) {
            throw RuntimeException()
        }
    }

    fun getAccessToken(userId: Long, authorities : List<String>) : String =
        tokenProvider.createJwtAccessTokenByUser(userId, authorities)
}