package jinheung.project.eureka.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @Value(value =  "\${test}")
    private val test : String = "null"

    @GetMapping("/test/a")
    fun testController() : ResponseEntity<String> {
        return ResponseEntity.ok(test)
    }

}