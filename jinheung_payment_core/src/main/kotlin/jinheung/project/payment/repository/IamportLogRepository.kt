package jinheung.project.payment.repository

import jinheung.project.payment.entity.IamportLog
import org.springframework.data.jpa.repository.JpaRepository

interface IamportLogRepository : JpaRepository<IamportLog, Long> {
}