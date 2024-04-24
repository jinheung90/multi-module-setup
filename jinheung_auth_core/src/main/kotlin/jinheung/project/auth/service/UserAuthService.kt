package jinheung.project.auth.service


import jinheung.project.auth.dto.UserAuthDto
import jinheung.project.auth.entity.*
import jinheung.project.auth.repository.AuthorityRepository
import jinheung.project.auth.repository.UserAuthorityRepository
import jinheung.project.auth.repository.UserRepository
import jinheung.project.auth.repository.UserSecurityRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.Flow


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

    
}