package jinheung.project.gateway.auth.r2dbc.entity


import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDateTime

@Table("user_securities")
data class UserSecurity(

    @Column("user_id")
    val userId : Long,
    @Column("email")
    val email : String,
    @Column("password")
    val password : String,
    @Column("created_at")
    @CreatedDate
    val createdAt : LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    @LastModifiedDate
    val updatedAt : LocalDateTime = LocalDateTime.now(),
    @Id
    val id : Long = 0
) {
    companion object {
        fun of(userId : Long, email: String, password: String) : UserSecurity {
            return UserSecurity(userId, email, password)
        }
    }
}