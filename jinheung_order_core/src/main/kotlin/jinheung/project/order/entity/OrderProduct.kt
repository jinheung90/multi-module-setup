package jinheung.project.order.entity

import java.math.BigDecimal
import jakarta.persistence.*

@Table(name = "cart_products")
@Entity
class OrderProduct {
    @Column(name = "product_id")
    val productId : Long = 0L
    @Column
    val quantity : Int = 1
    @Column
    val price : BigDecimal = BigDecimal.valueOf(1)
    @ManyToOne
    @JoinColumn(name = "order_id")
    val order: Order = Order()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}