package jinheung.project.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("authorities")
data class Authority(
    @Column("name")
    val name: String,
    @Id val id : Long = 0L,
) {
    companion object {
        fun of(name: String) : Authority {
            return Authority(name)
        }
    }
}