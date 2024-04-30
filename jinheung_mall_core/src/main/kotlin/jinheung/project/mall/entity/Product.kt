package jinheung.project.mall.entity


import org.hibernate.Hibernate
import java.math.BigDecimal
import jakarta.persistence.*



@Table(name = "products")
@Entity
class Product(
    @Column
    val price : BigDecimal = BigDecimal.valueOf(0L),
    @Column
    val quantity : Long = 0,
    @Column
    val name: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mall_id")
    val mall: Mall = Mall(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
)
