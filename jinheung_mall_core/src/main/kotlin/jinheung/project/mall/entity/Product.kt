package jinheung.project.mall.entity

import jinheung.project.mall.enums.Category
import org.hibernate.Hibernate
import java.math.BigDecimal
import javax.persistence.*



@Table(name = "products")
@Entity
data class Product(
    @Column
    val price : BigDecimal = BigDecimal.valueOf(0L),
    @Column
    val quantity : Long = 0,
    @Column
    val name: String = "",
    @Column
    @Enumerated(EnumType.STRING)
    val category: Category = Category.BOOK,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mall_id")
    val mall: Mall = Mall(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Product

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}