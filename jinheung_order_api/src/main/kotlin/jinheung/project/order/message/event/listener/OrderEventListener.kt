package jinheung.project.order.message.event.listener

import jinheung.project.event_client.kafka.listener.MainEventListener
import jinheung.project.event_common.enums.EventTopicType
import jinheung.project.order.dto.OrderDTO
import jinheung.project.order.service.OrderService
import org.springframework.stereotype.Component
import java.util.EventListener
import javax.annotation.PostConstruct


@Component
abstract class OrderEventListener(
    private val orderService: OrderService
) : MainEventListener<OrderDTO> (EventTopicType.ORDER) {

    override fun cancelCommand(t: OrderDTO) {
        println("test cancelCommand")
        orderService.cancelOrder(orderId = t.orderId)
    }
//
//    override fun recoveryCommand(t: OrderDTO): OrderDTO {
//        return OrderDTO()
//    }
//
//    override fun tryCommand(t: OrderDTO): OrderDTO {
//
//    }
}