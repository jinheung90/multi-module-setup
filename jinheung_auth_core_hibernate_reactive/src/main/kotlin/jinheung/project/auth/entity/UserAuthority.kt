package jinheung.project.auth.entity


import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "user_authorities")
class UserAuthority(
    @ManyToOne @Column val user: User,
    @ManyToOne @Column val authority: Authority
    ) : PanacheEntity() {
    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt : LocalDateTime = LocalDateTime.now()
    @Id @GeneratedValue @Column
    val id : Long = 0L
}