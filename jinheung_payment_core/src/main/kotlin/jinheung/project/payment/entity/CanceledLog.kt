package jinheung.project.payment.entity

import jinheung.project.payment.enums.PayErrorCode
import java.math.BigDecimal
import jakarta.persistence.*


@Table(name = "canceled_logs")
data class CanceledLog (
    @Column(name = "status_code")
    val statusCode :  Int,
    @Column(length = 2048)
    val message : String,
    @Column(name = "refund_money")
    val refundMoney : BigDecimal,
    @Column
    var isCanceled : Boolean = false,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
    companion object {
        fun of(statusCode: Int, message: String, refundMoney: BigDecimal) : CanceledLog {
            return CanceledLog(statusCode, message, refundMoney)
        }
    }
}