package jinheung.project.mall.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "malls_has_users")
//    uniqueConstraints = [
//        UniqueConstraint(
//            name = "mall_user_unique",
//            columnNames = [ "mall_id",  "user_id" ])
//    ])
data class MallHasUser(
    @Column(name = "user_id", nullable = false)
    val userId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mall_id")
    val mall: Mall = Mall(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as MallHasUser

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
    companion object {
        fun of(mall: Mall, userId: Long) : MallHasUser {
            return MallHasUser(userId, mall)
        }
    }
}