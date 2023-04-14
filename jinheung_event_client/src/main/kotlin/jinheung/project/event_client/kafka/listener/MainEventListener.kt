package jinheung.project.event_client.kafka.listener


import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import jinheung.project.event_client.interfaces.EventCommand
import jinheung.project.event_common.dto.EventCommonDTO
import jinheung.project.event_common.enums.EventCommandType
import jinheung.project.event_common.enums.EventTopicType
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.listener.MessageListener
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import kotlin.math.log


@Component
abstract class MainEventListener<T>() : EventCommand<T> {

    private var kafkaTemplateForEvent: KafkaTemplate<String, EventCommonDTO<T>>? = null
    private var eventTopicType: EventTopicType? = null
    private var tryTopic: String? = null
    private var cancelTopic: String? = null
    private var recoveryTopic: String? = null
    private var beenName: String? = null

    constructor(eventTopicType: EventTopicType) : this() {
        this.eventTopicType = eventTopicType
        val topicPrefix = eventTopicType.topicName + "-"
        this.tryTopic = topicPrefix + EventCommandType.TRY
        this.cancelTopic = topicPrefix + EventCommandType.CANCEL
        this.recoveryTopic = topicPrefix + EventCommandType.RECOVERY
        this.beenName = "$topicPrefix-message"
    }

    private class CustomMessageListener<T>(
        private val tryTopic: String,
        private val cancelTopic: String,
        private val recoveryTopic: String,
        private val mainEventListener: MainEventListener<T>
    ) : MessageListener<String, EventCommonDTO<T>> {
        override fun onMessage(data: ConsumerRecord<String, EventCommonDTO<T>>) {
            TODO("Not yet implemented")
            //TODO working
            if(data.topic().toString() == tryTopic) {
                mainEventListener.tryListener(data.value())
            } else if(data.topic().toString() == cancelTopic) {
                mainEventListener.cancelListener(data.value())
            } else if(data.topic().toString() == recoveryTopic){
                mainEventListener.recoveryListener(data.value())
            } else {
                println(null)
            }
            return
        }
    }

    @PostConstruct
    fun initKafka() {
        val topicPrefix = eventTopicType?.topicName + "-";
        val tryTopic = topicPrefix + EventCommandType.TRY
        val cancelTopic = topicPrefix + EventCommandType.CANCEL
        val recoveryTopic = topicPrefix + EventCommandType.RECOVERY
        val containerProperties = ContainerProperties(tryTopic, cancelTopic, recoveryTopic)
        containerProperties.messageListener = CustomMessageListener<T>(tryTopic,cancelTopic,recoveryTopic,this)

        val consumerFactory: ConsumerFactory<String, EventCommand<T>>
            = DefaultKafkaConsumerFactory(consumerProperties())
        val listenerContainer: KafkaMessageListenerContainer<String, EventCommand<T>>
            = KafkaMessageListenerContainer(consumerFactory, containerProperties)
        listenerContainer.isAutoStartup = false
        beenName?.let { listenerContainer.setBeanName(it) }
    }

    private fun consumerProperties(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.GROUP_ID_CONFIG] = "test"
        return props
    }

    fun tryListener(eventCommonDTO: EventCommonDTO<T>) {
        //TODO save commit
        val eventTopicType = EventTopicType.find(eventCommonDTO.eventType)
        val data = tryCommand(eventCommonDTO.data)
        kafkaTemplateForEvent?.send(eventTopicType.topicName, eventCommonDTO)
    }


    fun cancelListener(eventCommonDTO: EventCommonDTO<T>) {
        //TODO findByEventIdsAndSendCancelMessage
        val eventTopicType = EventTopicType.find(eventCommonDTO.eventType)
        val data = cancelCommand(eventCommonDTO.data)
        kafkaTemplateForEvent?.send(eventTopicType.topicName, eventCommonDTO)
    }

    fun recoveryListener(eventCommonDTO: EventCommonDTO<T>) {
        //TODO findById and update state
        val eventTopicType = EventTopicType.find(eventCommonDTO.eventType)
        val data = recoveryCommand(eventCommonDTO.data)
        kafkaTemplateForEvent?.send(eventTopicType.topicName, eventCommonDTO)
    }
}