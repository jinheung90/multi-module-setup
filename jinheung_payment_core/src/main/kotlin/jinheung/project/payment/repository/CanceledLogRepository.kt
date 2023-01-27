package jinheung.project.payment.repository

import jinheung.project.payment.entity.CanceledLog
import org.springframework.data.jpa.repository.JpaRepository

interface CanceledLogRepository : JpaRepository<CanceledLog, Long> {
}