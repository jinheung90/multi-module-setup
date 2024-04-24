package jinheung.project.auth.repository


import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jinheung.project.auth.entity.Authority
import jinheung.project.auth.entity.User
import org.springframework.stereotype.Repository
import java.util.concurrent.Flow

@Repository
interface AuthorityRepository : PanacheRepository<Authority> {
    suspend fun findAllByNameIn(name: List<String>): Uni<List<Authority>>
}