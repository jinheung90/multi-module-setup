package jinheung.project.auth.repository


import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending
import jinheung.project.auth.entity.Authority
import jinheung.project.auth.entity.User
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.stereotype.Repository
import java.util.concurrent.Flow

@Repository
class AuthorityRepository(private val sessionFactory: Mutiny.SessionFactory) : MutinyRepository<Authority, Long> {
    override suspend fun <S : Authority> save(entity: S): Authority {
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

    override suspend fun <S : Authority> saveAll(entities: Iterable<S>): List<Authority> {
        if(entities.none()) {
            throw RuntimeException("empty list")
        }
        return sessionFactory.withSession { s ->
            s.persistAll(entities).call(s::flush).replaceWith(entities)
        }.awaitSuspending().map { t -> t }.toList()
    }
}