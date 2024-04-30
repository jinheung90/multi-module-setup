package jinheung.project.auth.service






import org.hibernate.reactive.mutiny.Mutiny.SessionFactory
import jinheung.project.auth.dto.UserAuthDto
import jinheung.project.auth.entity.User

import org.springframework.stereotype.Service


@Service
class UserAuthService(
    private val sessionFactory: SessionFactory
    ) {


    suspend fun signup(email: String, password: String, name: String) : UserAuthDto {
//
//        val user: User = this.saveUser(name = name)
//        val authorities: List<Authority> = this.findAllByAuthorityName(UserRoles.signedUserRole())
//        val userAuthorities:List<UserAuthority> = this.saveUserAuthority(user = user, authorities = authorities)
//        val userSecurity = saveUserSecurity(user = user, password = password, email = email)
//
//        return UserAuthDto.of(userSecurity, authorities)\
//        val factory: Mutiny.SessionFactory = createEntityManagerFactory("auth")
//            .unwrap(Mutiny.SessionFactory::class.java)
        val cb = sessionFactory.withSession {
            session -> session.find(User::class.java, 1);
        }


        return UserAuthDto.empty();
    }


//    suspend fun saveUser(name: String) : User {
//        val user = User(name = name)
//        return userRepository.persist(user).awaitSuspending()
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
//        userAuthorityRepository.persist(userAuthorities).awaitSuspending()
//        return userAuthorities;
//    }
//
//    suspend fun saveUserSecurity(user: User, email: String, password: String) : UserSecurity {
//        val userSecurity = UserSecurity(user = user, email = email, password = password)
//        userSecurityRepository.persist(userSecurity).awaitSuspending()
//        return userSecurity
//    }
}