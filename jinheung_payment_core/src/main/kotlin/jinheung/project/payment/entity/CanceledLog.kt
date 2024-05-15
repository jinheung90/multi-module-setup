package jinheung.project.payment.entity

import jinheung.project.payment.enums.PayErrorCode
import java.math.BigDecimal
import jakarta.persistence.*


@Table(name = "canceled_logs")
class CanceledLog(
    @Column(name = "status_code")
    var statusCode: Int = 0,
    @Column(length = 2048)
    var message: String = "",
    @Column(name = "refund_money")
    var refundMoney: BigDecimal = BigDecimal.valueOf(0)
) {
    @Column
    var isCanceled: Boolean = true
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0L
}