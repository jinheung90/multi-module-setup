package jinheung.project.event_client.kafka.publisher


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import jinheung.project.event_common.dto.EventCommonDTO
import jinheung.project.event_common.enums.EventCommandType
import jinheung.project.event_common.enums.EventTopicType
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.stereotype.Component
import java.util.UUID
import javax.annotation.PostConstruct


@Component
abstract class MainEventPub<T>() {

    private var eventTopicType: EventTopicType? = null
    private var kafkaTemplateForEvent: KafkaTemplate<String, EventCommonDTO<T>>? = null

    constructor(eventTopicType: EventTopicType) : this() {
        this.eventTopicType = eventTopicType
    }

    @PostConstruct
    fun initKafka() {
        kafkaTemplateForEvent?.defaultTopic = eventTopicType.toString()
        kafkaTemplateForEvent = KafkaTemplate(producerFactory())
    }

    private fun producerFactory(): ProducerFactory<String, EventCommonDTO<T>> {
        val configProps: MutableMap<String, Any> = HashMap()
//        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    fun publishEvent(data: T) {
        kafkaTemplateForEvent?.send(eventTopicType?.topicName + EventCommandType.TRY,
            EventCommonDTO(
                listenerId = eventTopicType.toString(),
                eventId = UUID.randomUUID().toString(),
                data = data,
                eventType = EventCommandType.TRY
            )
        )
    }
}