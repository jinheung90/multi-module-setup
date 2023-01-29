package jinheung.project.order.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Address(
    private val city : String = "",
    private val district : String = "",
    @Column(name = "address_detail")
    private val detail : String = "",
    @Column(name = "zip_code")
    private val zipCode : String = ""
) {}