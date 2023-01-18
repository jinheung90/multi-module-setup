package jinheung.project.auth.entity


import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("users")
data class User (

    @Column("is_deleted")
    val isDeleted : Boolean = false,
    @Column("created_at")
    @CreatedDate
    val createdAt : LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    @LastModifiedDate
    val updatedAt : LocalDateTime  = LocalDateTime.now(),
    @Column("deleted_at")
    val deletedAt : LocalDateTime = LocalDateTime.now(),
    @Id
    val id : Long = 0L,
) {
    companion object {
        fun of() : User {
            return User()
        }
    }
}