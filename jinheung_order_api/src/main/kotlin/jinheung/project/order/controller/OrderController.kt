package jinheung.project.order.controller

import jinheung.project.order.dto.OrderDTO

import jinheung.project.order.service.OrderService
import jinheung.project.util.CustomSecuritySupport
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/order")
@RestController
class OrderController(
    private val orderService : OrderService,
) {

//    @PostMapping
//    fun order(@RequestBody orderDTO: OrderDTO) : ResponseEntity<String> {
//        val userId = CustomSecuritySupport.getUserId()
//        val result = orderService.order(orderDTO, userId)
//        orderEventPublisher.publishEvent(orderDTO)
//        return ResponseEntity.ok("result")
//    }
    @GetMapping
    fun kafkaTest() {
        println("test1211")
        println(OrderDTO.emptyObject().toString())
    }
}