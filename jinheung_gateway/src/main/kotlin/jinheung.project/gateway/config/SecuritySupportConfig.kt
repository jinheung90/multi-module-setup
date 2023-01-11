package jinheung.project.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@Configuration
class SecuritySupportConfig {
    @Bean
    fun passwordEncoder() : PasswordEncoder { return Pbkdf2PasswordEncoder() }
}