package jinheung.project

import jinheung.project.auth.filter.AuthFilterFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean


@SpringBootApplication
class JinheungGatewayApplication
fun main(args: Array<String>) {
	runApplication<JinheungGatewayApplication>(*args)
}
