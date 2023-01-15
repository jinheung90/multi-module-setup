package jinheung.project.gateway.config



import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteLocateConfig {
    @Bean
    fun customRouteLocator(builder : RouteLocatorBuilder): RouteLocator {
        return builder.routes {

            route(id = "auth") {
                path("/user/auth/**")
                uri("http://localhost:8080")
            }
            route(id = "user-profile") {
                path("/user/profile/**")
                uri("http://localhost:8081")
            }
        }
    }
}