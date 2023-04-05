package jinheung.project.order.event

import jinheung.project.event.kafka.listener.EventPub
import jinheung.project.order.dto.OrderDTO
import jinheung.project.order.service.OrderService
import org.springframework.stereotype.Component

@Component
abstract class OrderPub(
    private val orderService : OrderService
) : EventPub<OrderDTO>() {

    override fun tryCommand(t: OrderDTO, userId: Long): String {
        val orderDTO = orderService.order(orderDTO = t, userId = userId)
        return objectMapper.writeValueAsString(orderDTO)
    }

}