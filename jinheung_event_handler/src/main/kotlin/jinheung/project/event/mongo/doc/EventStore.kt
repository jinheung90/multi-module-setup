package jinheung.project.event.mongo.doc

import jinheung.project.event.dto.EventCommonDTO
import jinheung.project.event.enums.KafkaEventState
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigDecimal

@Document(value = "event_store")
data class EventStore(
    @Field(value =  "event_id") val eventId : String,
    @Field(value = "data") val data : String,
    @Field(value = "event_state") val kafkaEventState: KafkaEventState,
    @Field(value = "listener_id") val listenerId : String,
    @Id val id : String = "",
) {
    fun toDTO() : EventCommonDTO {
        return EventCommonDTO(eventId = this.eventId, listenerId = this.listenerId, data = this.data)
    }
}