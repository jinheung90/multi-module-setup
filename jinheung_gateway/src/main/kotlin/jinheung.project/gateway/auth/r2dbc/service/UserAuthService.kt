package com.example.jinheunggateway.auth.r2dbc.service

import com.example.jinheunggateway.auth.dto.UserAuthDto
import com.example.jinheunggateway.auth.r2dbc.entity.User
import com.example.jinheunggateway.auth.r2dbc.entity.UserAuthority
import com.example.jinheunggateway.auth.r2dbc.entity.UserSecurity
import jinheung.project.gateway.auth.r2dbc.repository.AuthorityRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserAuthorityRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserRepository
import jinheung.project.gateway.auth.r2dbc.repository.UserSecurityRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerResponse
import reactor.core.publisher.Mono
import java.util.concurrent.Flow


@Service
class UserAuthService(
        private val authorityRepository: AuthorityRepository,
        val userAuthorityRepository: UserAuthorityRepository,
        val userRepository: UserRepository,
        val userSecurityRepository: UserSecurityRepository,
        val passwordEncoder: PasswordEncoder
    ) {

    private final val commonUserAuthorities : List<String> = listOf("ROLE_USER")

    @Transactional
    suspend fun signup(email : String, password : String) : UserAuthDto {
        val user = userRepository.save(User());
        val authorities = authorityRepository.findAllByNameIn(commonUserAuthorities)

        userAuthorityRepository.saveAll(
                authorities.toList()
                    .map { a ->  UserAuthority.of(authorityId = a.id, userId = user.id) }
        )
        val userSecurity = userSecurityRepository
                .save(UserSecurity.of(user.id, email, passwordEncoder.encode(password)))
        return UserAuthDto.of(user.id, userSecurity, authorities.toList())
    }
}