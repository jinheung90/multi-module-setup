package jinheung.project.gateway.auth.router


import com.example.jinheunggateway.auth.dto.UserAuthDto
import jinheung.project.gateway.auth.r2dbc.service.UserAuthService
import jinheung.project.gateway.auth.dto.SignupRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAuthController(private val userAuthService : UserAuthService) {

    @GetMapping("/user/auth/signup")
    suspend fun signup(@RequestBody signupRequest: SignupRequest) :
        UserAuthDto = userAuthService.signup(signupRequest.email, signupRequest.password)
}