package jinheung.project.mall.entity

import org.hibernate.Hibernate
import javax.persistence.*



@Table(name = "malls")
data class Mall (
    @OneToMany
    val products : List<Product> = ArrayList(),
    @OneToMany
    val mallHasUsers: List<MallHasUser> = ArrayList(),
    @Column
    val name : String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Mall

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

    companion object {
        fun of(name: String) : Mall {
            return Mall(name = name)
        }
    }
}