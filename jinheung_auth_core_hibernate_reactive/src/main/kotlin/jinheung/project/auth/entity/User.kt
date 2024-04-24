package jinheung.project.auth.entity


import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "users")
class User(name: String) : PanacheEntity() {
    @Column
    val isDeleted: Boolean = false

    @Column
    val name: String = name

    @Column
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column
    val deletedAt: LocalDateTime = LocalDateTime.now()

    @Column
    @OneToMany
    val userAuthorities: List<UserAuthority> = listOf()

    @Id
    @GeneratedValue
    val id: Long = 0L

    companion object {
        fun create(name: String) : User {
            return User(name = name)
        }
    }
}