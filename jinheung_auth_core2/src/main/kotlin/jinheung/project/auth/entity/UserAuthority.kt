package jinheung.project.auth.entity




import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime



@Entity

class UserAuthority(
    @ManyToOne val user: User,
    @ManyToOne val authority: Authority
    )  {

    @CreationTimestamp
    val createdAt : LocalDateTime = LocalDateTime.now()
    @Id
    @GeneratedValue

    val id : Long = 0L
}