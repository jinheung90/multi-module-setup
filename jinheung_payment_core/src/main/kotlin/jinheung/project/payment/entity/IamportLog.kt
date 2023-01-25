package jinheung.project.payment.entity

import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "iamport_logs")
data class IamportLog (
    @Column(name = "imp_uid")
    val impUid : String,
    @Column(name = "merchant_uid")
    val merchantUid : String,
    @Column(name = "user_id")
    val userId : Long,
    @Column(name = "product_price")
    val productPrice : BigDecimal,
    @OneToOne
    @JoinColumn(name = "cancel_log_id")
    val canceledLog: CanceledLog? = null,
    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt: Instant? = Instant.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
    companion object {
        fun of(impUid: String, merchantUid: String, userId: Long, price: BigDecimal, canceledLog: CanceledLog?) : IamportLog {
            return IamportLog(impUid, merchantUid, userId, price, canceledLog)
        }
    }
}