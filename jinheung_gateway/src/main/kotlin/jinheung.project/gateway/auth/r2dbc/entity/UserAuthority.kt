package com.example.jinheunggateway.auth.r2dbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("user_authorities")
class UserAuthority(authorityId : Long, userId : Long) {
    @Id
    val id: Long = 0
    @Column("authority_id")
    val authorityId: Long = 0
    @Column("user_id")
    val userId : Long = 0
    companion object {
        fun of(authorityId : Long, userId : Long) : UserAuthority {
            return UserAuthority(authorityId, userId)
        }
    }
}