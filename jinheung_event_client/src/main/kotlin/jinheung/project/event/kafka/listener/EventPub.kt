package jinheung.project.event.kafka.listener


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import jinheung.project.event.dto.EventCommonDTO
import jinheung.project.event.dto.enums.KafkaCommandType
import jinheung.project.event.interfaces.EventCommand
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
abstract class EventPub<T>(
    protected var kafkaTemplateForEvent: KafkaTemplate<String, EventCommonDTO>? = null,
    protected var objectMapper: ObjectMapper = ObjectMapper()
) : EventCommand<T> {

    @PostConstruct
    fun initKafka() {
        val configProps: MutableMap<String, Any> = HashMap()
//        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        this.kafkaTemplateForEvent = KafkaTemplate<String, EventCommonDTO>(DefaultKafkaProducerFactory(configProps))
    }

    fun tryCommand(t: T, userId: Long) : String {
        return ""
    }

    fun cancelCommand(t: T, userId: Long) : String {
        return ""
    }
}