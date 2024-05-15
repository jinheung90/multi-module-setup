package jinheung.project.auth.repository

import io.smallrye.mutiny.coroutines.awaitSuspending
import jinheung.project.auth.entity.User
import jinheung.project.auth.repository.UserRepository

import org.hibernate.reactive.mutiny.Mutiny.SessionFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@Tag("integration")
@ActiveProfiles("local")
class UserRepositoryTest(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val sessionFactory: SessionFactory
) {
    @Test
    suspend fun saveTest() {
        sessionFactory.withSession {
            s -> s.persist(User())
        }.awaitSuspending()
    }
}

