package jinheung.project.order.repository

import jinheung.project.order.entity.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository

interface OrderProductRepository : JpaRepository<OrderProduct, Long> {
}