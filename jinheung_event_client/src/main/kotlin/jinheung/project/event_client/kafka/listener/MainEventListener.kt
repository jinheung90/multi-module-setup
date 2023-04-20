package jinheung.project.event_client.kafka.listener


import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import jinheung.project.event_client.interfaces.EventCommand
import jinheung.project.event_common.dto.EventCommonDTO
import jinheung.project.event_common.enums.EventCommandType
import jinheung.project.event_common.enums.EventTopicType
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.listener.MessageListener
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import kotlin.math.log


@Component
abstract class MainEventListener<T : EventCommonDTO>() : EventCommand<T> {

    private var kafkaTemplateForEvent: KafkaTemplate<String, T>? = null
    private var eventTopicType: EventTopicType? = null
    private var tryTopic: String? = null
    private var cancelTopic: String? = null
    private var recoveryTopic: String? = null
    private var beenName: String? = null

    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServer : String = ""

    constructor(eventTopicType: EventTopicType) : this() {
        this.eventTopicType = eventTopicType
        val topicPrefix = eventTopicType.topicName + "-"
        this.tryTopic = topicPrefix + EventCommandType.TRY
        this.cancelTopic = topicPrefix + EventCommandType.CANCEL
        this.recoveryTopic = topicPrefix + EventCommandType.RECOVERY
        this.beenName = eventTopicType.topicName
    }

    @PostConstruct
    fun initKafka() {
        val topicPrefix = eventTopicType?.topicName + "-";
        val tryTopic = topicPrefix + EventCommandType.TRY
        val cancelTopic = topicPrefix + EventCommandType.CANCEL
        val recoveryTopic = topicPrefix + EventCommandType.RECOVERY
        val containerProperties = ContainerProperties(tryTopic, cancelTopic, recoveryTopic)
        containerProperties.messageListener = getOnMessage()
        val consumerFactory: ConsumerFactory<String, T>
            = DefaultKafkaConsumerFactory(consumerProperties())
        val listenerContainer: KafkaMessageListenerContainer<String, T>
            = KafkaMessageListenerContainer(consumerFactory, containerProperties)
        listenerContainer.isAutoStartup = false
        beenName?.let { listenerContainer.setBeanName(it) }
    }

    private fun getOnMessage() : MessageListener<String, T> = MessageListener {
            data: ConsumerRecord<String, T> ->
        run {
            val topic = data.topic()
            println(topic.toString())
            if (tryTopic == topic) {
                this.tryListener(data.value())
            } else if (cancelTopic == topic) {
                this.cancelListener(data.value())
            } else if(recoveryTopic == topic) {
                this.recoveryListener(data.value())
            } else {
                println("error")
            }
        }
    }

    private fun consumerProperties(): Map<String, Any> {

        val props: MutableMap<String, Any> = HashMap()

        val listKafkaServer = bootstrapServer.split(',')
        listKafkaServer.stream().forEach {
                t -> println(t)
        }

        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = listKafkaServer
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return props
    }

    fun tryListener(t: T) {
        //TODO save commit
        val eventTopicType = EventTopicType.find(t.eventType)
        val data = tryCommand(t)
        kafkaTemplateForEvent?.send(eventTopicType.topicName, t)
    }


    fun cancelListener(t: T) {
        //TODO findByEventIdsAndSendCancelMessage
        val eventTopicType = EventTopicType.find(t.eventType)
        val data = cancelCommand(t)
        kafkaTemplateForEvent?.send(eventTopicType.topicName, t)
    }

    fun recoveryListener(t: T) {
        //TODO findById and update state
        val eventTopicType = EventTopicType.find(t.eventType)
        val data = recoveryCommand(t)
        kafkaTemplateForEvent?.send(eventTopicType.topicName, t)
    }
}