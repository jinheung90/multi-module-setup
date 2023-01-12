package jinheung.project.gateway.auth.router



import jinheung.project.gateway.auth.r2dbc.service.UserAuthService
import jinheung.project.gateway.auth.dto.SignupRequest
import jinheung.project.gateway.auth.dto.UserAuthDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAuthController(private val userAuthService : UserAuthService) {

    @PostMapping("/user/auth/signup")
    suspend fun signup(@RequestBody signupRequest: SignupRequest) :
            UserAuthDto = userAuthService.signup(signupRequest.email, signupRequest.password)
}