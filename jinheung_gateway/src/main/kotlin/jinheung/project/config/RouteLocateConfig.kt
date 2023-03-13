package jinheung.project.config



import jinheung.project.auth.filter.AuthFilterFactory
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteLocateConfig (
    private val authFilterFactory : AuthFilterFactory
) {
    @Bean
    fun customRouteLocator(builder : RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            route(id = "auth") {
                path("/user/auth/**")
                uri("http://localhost:8080")
            }
            route(id = "order") {
                path("/order")
                    .filters {
                        f -> f.filter(authFilterFactory.apply(AuthFilterFactory.Config()))
                    }
                uri("http://localhost:8082")
            }
        }
    }
}