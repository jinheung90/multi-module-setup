package jinheung.project.auth.config




import jinheung.project.auth.filter.AuthFilterFactory

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
class RouteLocateConfig (
    private val authFilterFactory : AuthFilterFactory
) {
//    @Bean
//    fun customRouteLocator(routeLocatorBuilder: RouteLocatorBuilder): RouteLocator {
//
//        return routeLocatorBuilder.routes {
//            route(id = "auth-service") {
//                path("/user/auth/**")
//                    .filters {
//                            f -> f.filter(authFilterFactory.apply(AuthFilterFactory.Config()))
//                    }
//                uri("http://localhost:8082//user/auth")
//            }
//            route(id = "mall-service") {
//                path("/mall/**")
//                    .filters {
//                            f -> f.filter(authFilterFactory.apply(AuthFilterFactory.Config()))
//                    }
//                uri("http://localhost:8082/mall")
//            }
//            route(id = "order-service") {
//                path("/order/**")
//                    .filters {
//                            f -> f.filter(authFilterFactory.apply(AuthFilterFactory.Config()))
//                    }
//                uri("http://localhost:8084/order")
//            }
//        }
//    }
}