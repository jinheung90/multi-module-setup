package jinheung.project.gateway.auth.dto

import jinheung.project.gateway.auth.r2dbc.entity.Authority
import jinheung.project.gateway.auth.r2dbc.entity.UserSecurity

class UserAuthDto(userId: Long, userAuthorities: List<String>, email: String) {


    val userId : Long = userId
    val userAuthorities : List<String> = userAuthorities
    val userSecurity : UserSecurityDto = UserSecurityDto(email)

    class UserSecurityDto(email: String) {
        val email : String = email
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