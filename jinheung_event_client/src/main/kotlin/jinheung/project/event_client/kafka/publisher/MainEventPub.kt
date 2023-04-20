package jinheung.project.event_client.kafka.publisher



import com.google.gson.JsonSerializer
import jinheung.project.event_common.dto.EventCommonDTO
import jinheung.project.event_common.enums.EventCommandType
import jinheung.project.event_common.enums.EventTopicType
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import kotlin.collections.HashMap


@Component
class MainEventPub<T : EventCommonDTO> {

    private var eventTopicType: EventTopicType = EventTopicType.ORDER
    private var kafkaTemplateForEvent: KafkaTemplate<String, T >? = null

    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private val bootstrapAddress: String = ""

    fun setTopic(eventTopicType: EventTopicType) {
        this.eventTopicType = eventTopicType;
    }


    @PostConstruct
    fun initKafka() {
        this.kafkaTemplateForEvent = KafkaTemplate(producerFactory())
//
    }

    private final fun producerFactory(): ProducerFactory<String, T> {
        val configProps: MutableMap<String, Any> = HashMap()
        val listKafkaServer = bootstrapAddress.split(',')
        listKafkaServer.stream().forEach {
                t -> println(t)
        }
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = listKafkaServer
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = EventCommonDTO::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    fun publishEvent(data: T) {
        println(eventTopicType.toString())
        println(data.toString())
        kafkaTemplateForEvent?.send(
            eventTopicType.topicName + "-" + EventCommandType.TRY,
            data
        )
    }


}