package com.example.jinheunggateway.auth.dto

import com.example.jinheunggateway.auth.r2dbc.entity.Authority
import com.example.jinheunggateway.auth.r2dbc.entity.User
import com.example.jinheunggateway.auth.r2dbc.entity.UserAuthority
import com.example.jinheunggateway.auth.r2dbc.entity.UserSecurity

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