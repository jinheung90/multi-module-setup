package jinheung.project.order.message.event.listener

import jinheung.project.event_client.kafka.listener.MainEventListener
import jinheung.project.event_common.enums.EventTopicType
import jinheung.project.order.dto.OrderDTO
import org.springframework.stereotype.Component
import java.util.EventListener
import javax.annotation.PostConstruct



abstract class OrderEventListener : MainEventListener<OrderDTO> (EventTopicType.ORDER) {

    override fun cancelCommand(t: OrderDTO) {
        TODO("Not yet implemented")
        println("test cancelCommand")
    }
}