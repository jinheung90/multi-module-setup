package jinheung.project.auth.service






//import jinheung.project.auth.entity.Authority
//import jinheung.project.auth.entity.UserAuthority

import jinheung.project.auth.dto.UserAuthDto
import jinheung.project.auth.entity.Authority
import jinheung.project.auth.entity.User
import jinheung.project.auth.entity.UserAuthority
import jinheung.project.auth.repository.UserAuthorityRepository
import jinheung.project.auth.repository.UserRepository
import jinheung.project.enums.UserRoles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory
import org.springframework.stereotype.Service


@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val userAuthorityRepository: UserAuthorityRepository,
    ) {
//    suspend fun signup(email: String, password: String, name: String) : UserAuthDto {
//
//        val user: User = this.saveUser(name = name)
////        val authorities: List<Authority> = this.findAllByAuthorityName(UserRoles.signedUserRole())
////        val userAuthorities:List<UserAuthority> = this.saveUserAuthority(user = user, authorities = authorities)
////        val userSecurity = saveUserSecurity(user = user, password = password, email = email)
//
//        return UserAuthDto.of(userSecurity, authorities)
//
//    }


//    suspend fun saveUser(name: String) : User {
//        return userRepository.save(user(name = name));
//    }
//
//    suspend fun findAllByAuthorityName(names: List<String>): List<Authority> {
//        return authorityRepository.findAllByNameIn(names).awaitSuspending()
//    }
//
//    suspend fun saveUserAuthority(authorities: List<Authority>, user: User): List<UserAuthority> {
//        val userAuthorities = authorities.map {
//                authority -> UserAuthority(authority = authority, user = user)
//        }
//
//        return userAuthorities;
//    }
//
//    suspend fun saveUserSecurity(user: User, email: String, password: String) : UserSecurity {
//        val userSecurity = UserSecurity(user = user, email = email, password = password)
//
//        return userSecurity
//    }
}