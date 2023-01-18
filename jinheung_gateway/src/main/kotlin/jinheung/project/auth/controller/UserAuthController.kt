package jinheung.project.auth.controller



import jinheung.project.auth.dto.LoginDto
import jinheung.project.auth.dto.SignupRequest
import jinheung.project.auth.dto.UserAuthDto
import jinheung.project.auth.service.UserAuthService


import jinheung.project.jwt.TokenProvider
import kotlinx.coroutines.flow.toList
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user/auth")
class UserAuthController(
    private val userAuthService: UserAuthService,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider

    ) {

    @PostMapping("/signup/email")
    suspend fun signupFromEmail(@RequestBody signupRequest: SignupRequest) : UserAuthDto {
        val newPassword = passwordEncoder.encode(signupRequest.password)
        return userAuthService.signup(signupRequest.email, newPassword)
    }
    @PostMapping("/login/email")
    suspend fun loginFromEmail(@RequestBody loginDto: LoginDto) : Map<String,String> {
        val userSecurity = userAuthService.findUserSecurityByEmail(loginDto.email)
        if(!passwordEncoder.matches(loginDto.password, userSecurity.password)) {
            throw RuntimeException()
        }

        val authorities = userAuthService.findUserAuthoritiesByUserSecurity(userSecurity);
        val token = tokenProvider.createJwtAccessTokenByUser(userSecurity.userId, authorities.toList().map { a -> a.name })
        val response = HashMap<String, String>()
        response["access_token"] = token
        return response
    }
}