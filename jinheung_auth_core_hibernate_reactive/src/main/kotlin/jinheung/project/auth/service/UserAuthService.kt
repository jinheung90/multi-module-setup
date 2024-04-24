package jinheung.project.auth.service


import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending
import jinheung.project.auth.dto.UserAuthDto
import jinheung.project.auth.entity.*
import jinheung.project.auth.repository.AuthorityRepository
import jinheung.project.auth.repository.UserAuthorityRepository
import jinheung.project.auth.repository.UserRepository

import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.transaction.Transactional


@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val authorityRepository: AuthorityRepository,
    private val userAuthorityRepository: UserAuthorityRepository
    ) {

    final val commonAuthorityList: List<String> = listOf("ROLE_USER", "ROLE_COMMON")
    final val adminAuthorityList: List<String> = listOf("ROLE_USER", "ROLE_ADMIN", "ROLE_COMMON")

    @Transactional
    suspend fun signup(email: String, password: String, name: String) { // : UserAuthDto {

        val user: User = this.saveUser(name = name)
        val authorities: List<Authority> = this.findAllByAuthorityName(commonAuthorityList)
        val userAuthorities:List<UserAuthority> = this.saveUserAuthority(user = user, authorities = authorities)
//        val userSecurity =
//            .save(UserSecurity.of(user.id, email, password))
//        return UserAuthDto.of(userSecurity.userId, userSecurity, authorities.toList())
//        return UserAuthDto()
    }

    suspend fun saveUser(name: String) : User {
        val user = User.create(name)
        return userRepository.persist(user).awaitSuspending()
    }

    suspend fun findAllByAuthorityName(names: List<String>): List<Authority> {
        return authorityRepository.findAllByNameIn(names).awaitSuspending();
    }

    suspend fun saveUserAuthority(authorities: List<Authority>, user: User): List<UserAuthority> {
        val userAuthorities = authorities.map {
                authority -> UserAuthority(authority = authority, user = user)
        }
        userAuthorityRepository.persist(userAuthorities).awaitSuspending()
        return userAuthorities;
    }
    suspend fun saveUserSecurity(user: User) {

    }
}