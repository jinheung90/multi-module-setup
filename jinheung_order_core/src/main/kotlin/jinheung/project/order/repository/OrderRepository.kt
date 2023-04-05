package jinheung.project.order.repository

import jinheung.project.order.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}