package jinheung.project

import jakarta.persistence.Persistence
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication
class JinheungAuthApplication
fun main(args: Array<String>) {
	runApplication<JinheungAuthApplication>(*args)

}
