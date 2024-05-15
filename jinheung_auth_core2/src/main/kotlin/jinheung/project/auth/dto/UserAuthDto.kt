package jinheung.project.auth.dto

import jinheung.project.auth.entity.Authority
import jinheung.project.auth.entity.UserSecurity

//import jinheung.project.auth.entity.Authority
//import jinheung.project.auth.entity.UserSecurity

class UserAuthDto(val id: Long, val userAuthorities: List<String>, email: String) {



//   class UserSecurityDto(val email: String) {
//      companion object  {
//         fun of(userSecurity : UserSecurity) : UserSecurityDto {
//            return UserSecurityDto(userSecurity.email);
//         }
//      }
//   }
//
   companion object  {
      fun of(userSecurity: UserSecurity, authorities: List<Authority>) : UserAuthDto {
         return UserAuthDto(userSecurity.user.id, authorities.map {  a -> a.name }, userSecurity.email)
      }

   }
}