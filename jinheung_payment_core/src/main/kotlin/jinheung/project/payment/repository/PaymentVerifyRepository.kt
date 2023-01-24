package jinheung.project.payment.repository

import jinheung.project.payment.entity.PaymentVerify
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentVerifyRepository : JpaRepository<PaymentVerify, Long> {
}