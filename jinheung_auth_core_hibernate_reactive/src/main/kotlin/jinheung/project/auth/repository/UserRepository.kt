package jinheung.project.auth.repository


import io.quarkus.hibernate.reactive.panache.PanacheRepository
import jinheung.project.auth.entity.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PanacheRepository<User> {

}