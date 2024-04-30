package jinheung.project.auth.entity




import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

import jakarta.persistence.*


@Entity
class User(val name: String) {

    val createdAt: LocalDateTime = LocalDateTime.now()
    val updatedAt: LocalDateTime = LocalDateTime.now()
    val deletedAt: LocalDateTime = LocalDateTime.now()

    @OneToMany
    val userAuthorities: List<UserAuthority> = listOf()

    val isDeleted: Boolean = false

    @Id
    @GeneratedValue
    val id: Long = 0L
}