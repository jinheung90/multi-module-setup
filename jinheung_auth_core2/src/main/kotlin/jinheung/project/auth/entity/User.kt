package jinheung.project.auth.entity




import com.fasterxml.jackson.databind.ser.Serializers.Base
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.math.BigInteger


@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
    var createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime = LocalDateTime.now()
    var deletedAt: LocalDateTime = LocalDateTime.now()
    @OneToMany(cascade = [CascadeType.PERSIST])
    var userAuthorities: MutableList<UserAuthority> = ArrayList()
    @OneToMany(cascade = [CascadeType.PERSIST])
    var userSecurities: MutableList<UserSecurity> = ArrayList()
    @Column
    var isDeleted: Boolean = false
}