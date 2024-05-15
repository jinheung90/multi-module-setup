package jinheung.project.order.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val city : String = "",
    val district : String = "",
    @Column(name = "address_detail")
    val detail : String = "",
    @Column(name = "zip_code")
    val zipCode : String = ""
) {}