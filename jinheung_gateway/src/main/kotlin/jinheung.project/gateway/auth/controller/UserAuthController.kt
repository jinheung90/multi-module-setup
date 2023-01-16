package jinheung.project.gateway.auth.controller



import jinheung.project.gateway.auth.dto.LoginDto
import jinheung.project.gateway.auth.dto.SignupRequest
import jinheung.project.gateway.auth.dto.UserAuthDto

import jinheung.project.gateway.auth.r2dbc.service.UserAuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user/auth")
class UserAuthController(private val userAuthService: UserAuthService) {


    @PostMapping("/signup/email")
    suspend fun signupFromEmail(@RequestBody signupRequest: SignupRequest) : UserAuthDto {

        return userAuthService.signup(signupRequest.email,signupRequest.password)
    }
    @PostMapping("/login/email")
    suspend fun loginFromEmail(@RequestBody loginDto: LoginDto) : HashMap<String,String> {
        return userAuthService.loginWithPassword(loginDto.email,loginDto.password)
    }
}