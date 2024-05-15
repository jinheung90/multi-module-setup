package jinheung.project.auth.entity




import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime



@Entity
@Table(name= "user_authorities")
class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    @ManyToOne @JoinColumn(name = "user_id")
    var user: User = User()

    @ManyToOne @JoinColumn(name = "authority_id")
    var authority: Authority = Authority()

    @CreationTimestamp
    val createdAt : LocalDateTime = LocalDateTime.now()
}