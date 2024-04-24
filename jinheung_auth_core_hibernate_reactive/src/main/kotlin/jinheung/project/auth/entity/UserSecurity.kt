package jinheung.project.auth.entity





import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "user_securities")
class UserSecurity : PanacheEntity() {
    @Column(name = "user_id")
    val userId: Long = 0

    @Column(name = "email")
    val email: String = ""

    @Column(name = "password")
    val password: String = ""

    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Id @GeneratedValue @Column
    val id: Long = 0
}