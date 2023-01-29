package jinheung.project.order.entity

import javax.persistence.*

@Table(name = "cart_products")
data class CartProduct(
    @Column(name = "product_id")
    val productId : Long = 0L,
    @Column
    val quantity : Int = 1,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
}