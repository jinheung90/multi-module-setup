package jinheung.project.auth.repository

import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending


import jinheung.project.auth.entity.User
import kotlinx.coroutines.flow.merge
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory

import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
class UserRepository(private val sessionFactory: SessionFactory): MutinyRepository<User, Long> {

     override suspend fun <S : User> save(entity: S): User {
        if(entity.id == 0L || entity.id == null) {
            return sessionFactory.withSession {
                    s -> s.persist(entity)
                        .onItem()
                        .call(s::flush)
                        .replaceWith(entity)
            }.awaitSuspending()
        }

        return sessionFactory.withSession {
            s -> s.merge(entity).onItem().call(s::flush)
        }.awaitSuspending()
    }

    override suspend fun <S : User> saveAll(entities: Iterable<S>): List<User> {
        if(entities.none()) {
            throw RuntimeException("empty list")
        }
        return sessionFactory.withSession { s ->
            s.persistAll(entities).call(s::flush).replaceWith(entities)
        }.awaitSuspending().toList()
    }
}


