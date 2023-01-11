package com.example.jinheunggateway.auth.r2dbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("authorities")
class Authority(name: String) {
    @Id
    val id: Long = 0
    val name: String = ""
}