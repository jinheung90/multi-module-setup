package jinheung.project.auth.dto

import jinheung.project.auth.entity.Authority
import jinheung.project.auth.entity.UserSecurity

class UserAuthDto(val userId: Long, val userAuthorities: List<String>, email: String) {

    val userSecurity : UserSecurityDto = UserSecurityDto(email)

    class UserSecurityDto(val email: String) {
        companion object  {
            fun of(email : String) : UserSecurityDto {
                return UserSecurityDto(email);
            }
        }
    }

    companion object  {
        fun of(userId : Long, userSecurity: UserSecurity, authorities: List<Authority>) : UserAuthDto {
            return UserAuthDto(userId, authorities.map {  a -> a.name }, userSecurity.email)
        }
    }
}