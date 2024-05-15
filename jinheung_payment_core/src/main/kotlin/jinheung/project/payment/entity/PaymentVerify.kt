package jinheung.project.payment.entity

import org.hibernate.Hibernate
import jakarta.persistence.*


@Entity
@Table(name = "payment_verify_logs")
class PaymentVerify (
    val name : String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {


}