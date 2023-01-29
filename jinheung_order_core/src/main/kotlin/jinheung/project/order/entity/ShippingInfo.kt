package jinheung.project.order.entity

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "shipping_info")
data class ShippingInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,
    @Embedded
    val address: Address = Address(),
    @Column(name = "request_memo")
    val requestMemo : String = "",
) {
}