package jinheung.project.auth.repository

import io.smallrye.mutiny.Uni
import kotlinx.coroutines.flow.Flow
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


interface MutinyRepository<T,ID> {
    suspend fun <S : T> save(entity: S): T
    suspend fun <S : T> saveAll(entities: Iterable<S>): List<T>

//    suspend fun findById(id: ID): T?
//
//    suspend fun existsById(id: ID): Boolean
//
//    fun findAll(): Flow<T>

//    fun findAllById(ids: Iterable<ID>): Flow<T>
//
//    fun findAllById(ids: Flow<ID>): Flow<T>
//
//    suspend fun count(): Long
//
//    suspend fun deleteById(id: ID)
//
//    suspend fun delete(entity: T)
//
//    suspend fun deleteAllById(ids: Iterable<ID>)
//
//    suspend fun deleteAll(entities: Iterable<T>)
//
//    suspend fun <S : T> deleteAll(entityStream: Flow<S>)
//
//    suspend fun deleteAll()
}