package jinheung.project.auth.entity






import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import jakarta.persistence.*


@Entity
class UserSecurity(
   @ManyToOne
   @JoinColumn(name = "user_id")
   val user: User,


   val email: String,


   val password: String,
) {

   @CreationTimestamp
   val createdAt: LocalDateTime = LocalDateTime.now()


   @UpdateTimestamp
   val updatedAt: LocalDateTime = LocalDateTime.now()

   @Id
   @GeneratedValue
   @Column
   val id: Long = 0
}
