package jinheung.project.auth.service


import jinheung.project.auth.dto.UserAuthDto
import jinheung.project.auth.entity.*
import jinheung.project.auth.entity.repository.AuthorityRepository
import jinheung.project.auth.entity.repository.UserAuthorityRepository
import jinheung.project.auth.entity.repository.UserRepository
import jinheung.project.auth.entity.repository.UserSecurityRepository


import kotlinx.coroutines.flow.*

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserAuthService(
    private val authorityRepository: AuthorityRepository,
    private val userAuthorityRepository: UserAuthorityRepository,
    private val userRepository: UserRepository,
    private val userSecurityRepository: UserSecurityRepository
    ) {

    private final val commonUserAuthorities : List<String> = listOf("ROLE_USER")

    @Transactional
    suspend fun signup(email: String, password: String) : UserAuthDto {

        val user = userRepository.save(User.of())

        val authorities = authorityRepository.findAllByNameIn(commonUserAuthorities)

        val userAuthorities = authorities
            .map { a ->  UserAuthority.of(authorityId = a.id, userId = user.id) }

        userAuthorityRepository.saveAll(userAuthorities).toList()

        val userSecurity = userSecurityRepository
                .save(UserSecurity.of(user.id, email, password))
        return UserAuthDto.of(userSecurity.userId, userSecurity, authorities.toList())
    }

    @Transactional
    suspend fun findUserAuthoritiesByUserSecurity(userSecurity: UserSecurity) : Flow<Authority> {
        return authorityRepository.findAllByUserId(userSecurity.userId)
    }

    @Transactional
    suspend fun findUserSecurityByEmail(email: String) : UserSecurity {
        return userSecurityRepository.findAllByEmail(email).first()
    }
}