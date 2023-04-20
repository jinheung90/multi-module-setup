package jinheung.project.auth.entity.repository;

import jinheung.project.auth.entity.Authority
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param

interface AuthorityRepository : CoroutineCrudRepository<Authority, Long> {
    fun findAllByNameIn(names : List<String>) : Flow<Authority>
    @Query("select * from authorities a " +
            "inner join user_authorities ua " +
            "on a.id = ua.authority_id " +
            "and ua.user_id = :userId")
    fun findAllByUserId(@Param(value = "userId") userId : Long) : Flow<Authority>
}