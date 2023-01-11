package com.example.jinheunggateway.auth.r2dbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDateTime

@Table("user_securities")
class UserSecurity(userId: Long, email: String, password: String) {

    @Id
    val id: Long = 0
    @Column("user_id")
    val userId : Long = 0
    val email : String = ""
    val password : String = ""
    val createdAt : Instant = Instant.now()
    val updatedAt : Instant = Instant.now()

    companion object {
        fun of(userId : Long, email: String, password: String) : UserSecurity {
            return UserSecurity(userId, email, password)
        }
    }
}