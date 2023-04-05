package jinheung.project.event.kafka.listener

import com.fasterxml.jackson.databind.ser.std.StringSerializer
import jinheung.project.event.dto.EventCommonDTO
import jinheung.project.event.dto.enums.KafkaCommandType
import jinheung.project.event.mongo.service.EventStoreService
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.stereotype.Component


@Component
abstract class EventListener(
    private val eventStoreService: EventStoreService,
    private val kafkaTemplateForEvent: KafkaTemplate<String, EventCommonDTO>
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, EventCommonDTO> {
        val configProps: MutableMap<String, Any> = HashMap()
//        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplateForEvent() = KafkaTemplate(producerFactory())

    @KafkaListener(id = KafkaCommandType.ID, topics = [KafkaCommandType.TRY])
    fun tryListener(eventCommonDTO: EventCommonDTO) {
        //TODO save commit
        eventStoreService.saveEventStore(eventId = eventCommonDTO.eventId, data = eventCommonDTO.data, listenerId = eventCommonDTO.listenerId)
            .subscribe()
    }

    @KafkaListener(id = KafkaCommandType.ID, topics = [KafkaCommandType.CANCEL])
    fun cancelListener(eventCommonDTO: EventCommonDTO) {
        eventStoreService.findAllEventsByEventId(eventCommonDTO.eventId).map {
            evenStore -> {
                kafkaTemplateForEvent.send(KafkaCommandType.ID, evenStore.toDTO())
            }
        }
    }

    @KafkaListener(id = KafkaCommandType.ID, topics = [KafkaCommandType.RECOVERY])
    fun recoveryListener() {
        //TODO findById and update state
    }
}