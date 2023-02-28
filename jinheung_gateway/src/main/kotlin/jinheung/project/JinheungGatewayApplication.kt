package jinheung.project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean


@SpringBootApplication
class JinheungGatewayApplication
fun main(args: Array<String>) {
	runApplication<JinheungGatewayApplication>(*args)

	@Bean
	fun customRouteLocator(builder : RouteLocatorBuilder): RouteLocator {
		return builder.routes {
			route(id = "auth") {
				path("/user/auth/**")
				uri("http://localhost:8080")
			}
			route(id = "order") {
				path("/order")
				uri("http://localhost:8082")
			}
		}
	}
}
