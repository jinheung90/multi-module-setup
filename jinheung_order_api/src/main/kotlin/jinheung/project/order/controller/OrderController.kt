package jinheung.project.order.controller
//
//import jinheung.project.event_client.kafka.publisher.MainEventPub
import jinheung.project.event_client.kafka.publisher.MainEventPub
import jinheung.project.event_common.enums.EventTopicType
import jinheung.project.order.dto.OrderDTO
import jinheung.project.order.message.event.publisher.OrderEventPublisher

import jinheung.project.order.service.OrderService
import jinheung.project.util.CustomSecuritySupport
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/order")
@RestController
class OrderController(
    private val orderService : OrderService,
    private val orderEventPublisher: OrderEventPublisher
) {

    @PostMapping
    fun order(@RequestBody orderDTO: OrderDTO) : ResponseEntity<String> {
        val userId = CustomSecuritySupport.getUserId()
        val result = orderService.order(orderDTO, userId)
        orderEventPublisher.publishEvent(orderDTO)
        return ResponseEntity.ok("result")
    }
}