package jinheung.project.order.entity

import java.math.BigDecimal
import jakarta.persistence.*

@Table(name = "orders")
@Entity
class Order (
    @Column(name = "user_id")
    val userId : Long = 0L,
    @Embedded
    val address: Address = Address(),
    @Column(name = "request_memo")
    val requestMemo : String = "",
    @Column(name = "is_success")
    var success: Boolean = true,
    @OneToMany
    val orderProducts : List<OrderProduct> = listOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
)