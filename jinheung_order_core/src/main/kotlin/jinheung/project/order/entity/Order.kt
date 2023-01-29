package jinheung.project.order.entity

import javax.persistence.*

@Table(name = "orders")
data class Order (
    @Column(name = "user_id")
    val userId : String = "",
    @OneToOne
    @JoinColumn(name = "cart_id")
    val cart: Cart = Cart(),
    @OneToOne
    @JoinColumn(name = "shipping_info_id")
    val shippingInfo: ShippingInfo = ShippingInfo(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

) {
}