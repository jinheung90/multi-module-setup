package jinheung.project.order.service

import jinheung.project.order.dto.OrderDTO
import jinheung.project.order.entity.Address
import jinheung.project.order.entity.Order
import jinheung.project.order.entity.OrderProduct
import jinheung.project.order.repository.OrderProductRepository
import jinheung.project.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Service
class OrderService (
    private val orderProductRepository: OrderProductRepository,
    private val orderRepository: OrderRepository
    ){

    @Transactional
    fun order(orderDTO: OrderDTO, userId : Long) : OrderDTO {
        val order = saveOrder(orderDTO.address, orderDTO.requestMemo, userId)
        val orderProducts = saveOrderProducts(orderDTO.orderProducts, order)
        return OrderDTO.fromEntity(order, orderProducts)
    }

    @Transactional
    fun saveOrder(address: Address, requestMemo: String, userId: Long) : Order {
        return orderRepository.save(Order.of(userId, address, requestMemo))
    }

    @Transactional
    fun saveOrderProducts(orderProducts : List<OrderDTO.OrderProductDTO>, order: Order) : List<OrderProduct> {
        return orderProductRepository.saveAll(
            orderProducts.stream().map { op ->
                OrderProduct.of(productId = op.productId, op.quantity, order, op.price)
            }.toList()
        )
    }

    @Transactional
    fun cancelOrder(orderId: Long) {
        val order = findFirstById(orderId)
        order.success = false
    }

    @Transactional
    fun findFirstById(orderId: Long) : Order {
        return orderRepository.findById(orderId).get()
    }
}