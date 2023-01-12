package jinheung.project.gateway.auth.r2dbc.repository;

import jinheung.project.gateway.auth.r2dbc.entity.Authority
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorityRepository : CoroutineCrudRepository<Authority, Long> {
    fun findAllByNameIn(names : List<String>) : Flow<Authority>
}