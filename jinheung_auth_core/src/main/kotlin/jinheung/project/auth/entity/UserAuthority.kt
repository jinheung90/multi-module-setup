package jinheung.project.auth.entity


import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("user_authorities")
data class UserAuthority(
    @Column("authority_id")
    val authorityId: Long,
    @Column("user_id")
    val userId : Long,
    @Column("created_at")
    @CreatedDate
    val createdAt : LocalDateTime = LocalDateTime.now(),
    @Id
    val id : Long = 0L
    ) {
    companion object {
        fun of(authorityId : Long, userId : Long) : UserAuthority {
            return UserAuthority(authorityId = authorityId, userId = userId)
        }
    }
}