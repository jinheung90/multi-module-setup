package jinheung.project.order.controller

import jinheung.project.order.dto.OrderDTO
import jinheung.project.order.event.OrderPub
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/order")
class OrderController(
    private val orderPub: OrderPub
) {
    @PostMapping
    fun orderProduct(@RequestBody orderDTO: OrderDTO) {
        orderPub.tryCommand(orderDTO)
    }

}