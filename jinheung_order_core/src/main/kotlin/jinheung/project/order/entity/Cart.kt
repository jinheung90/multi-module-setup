package jinheung.project.order.entity

import javax.persistence.*
@Table(name = "carts")
data class Cart(
    @Column(name = "user_id")
    val userId : String = "",
    @OneToMany
    val cartProducts : List<CartProduct> = listOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
}