package jinheung.project.event.mongo.repository

import jinheung.project.event.mongo.doc.EventStore
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface EventStoreRepository : ReactiveMongoRepository<EventStore, String> {
    fun findAllByEventId(eventId : String) : Flux<EventStore>
}