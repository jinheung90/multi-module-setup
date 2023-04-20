package jinheung.project.order.message.event.publisher

//import jinheung.project.event_client.kafka.publisher.MainEventPub
import jinheung.project.event_client.kafka.publisher.MainEventPub
import jinheung.project.event_common.enums.EventTopicType
import jinheung.project.order.dto.OrderDTO
import org.springframework.stereotype.Component

@Component
class OrderEventPublisher() : MainEventPub<OrderDTO>() {

}