package jinheung.project.order.message

import jinheung.project.event_client.kafka.publisher.MainEventPub
import jinheung.project.event_common.enums.EventTopicType
import jinheung.project.order.dto.OrderDTO
import jinheung.project.order.service.OrderService
import jinheung.project.util.CustomSecuritySupport
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Component
abstract class OrderPub(
    private val orderService : OrderService
) : MainEventPub<OrderDTO>(eventTopicType = EventTopicType.ORDER) {

    @PostMapping("/order")
    fun order(@RequestBody orderDTO: OrderDTO) : ResponseEntity<OrderDTO> {
        val userId = CustomSecuritySupport.getUserId()
        val result = orderService.order(orderDTO, userId)
        this.publishEvent(result)
        return ResponseEntity.ok(result)
    }
}