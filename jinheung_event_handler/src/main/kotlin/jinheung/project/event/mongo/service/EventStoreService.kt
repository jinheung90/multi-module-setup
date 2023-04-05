package jinheung.project.event.mongo.service

import jinheung.project.event.enums.KafkaEventState
import jinheung.project.event.mongo.doc.EventStore
import jinheung.project.event.mongo.repository.EventStoreRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EventStoreService(
    private val eventStoreRepository: EventStoreRepository
) {

    fun saveEventStore(eventId : String, data : String, listenerId : String) : Mono<EventStore> {
        return eventStoreRepository.save(EventStore(eventId = eventId, data = data, kafkaEventState = KafkaEventState.TRY, listenerId = listenerId))
    }

    fun findAllEventsByEventId(eventId: String) : Flux<EventStore> {
        return eventStoreRepository.findAllByEventId(eventId)
    }
}