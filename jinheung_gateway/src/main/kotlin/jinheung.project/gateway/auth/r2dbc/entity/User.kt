package com.example.jinheunggateway.auth.r2dbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
class User {
    @Id
    val id: Long = 0
}