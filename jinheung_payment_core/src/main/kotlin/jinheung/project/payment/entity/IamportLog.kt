package jinheung.project.payment.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import jakarta.persistence.*

@Table(name = "iamport_logs")
class IamportLog(
    @Column(name = "imp_uid")
    val impUid: String,
    @Column(name = "merchant_uid")
    val merchantUid: String,
    @Column(name = "user_id")
    val userId: Long,
    @OneToOne
    @JoinColumn(name = "cancel_log_id")
    val canceledLog: CanceledLog? = null,
) {
    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt: Instant? = Instant.now()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}