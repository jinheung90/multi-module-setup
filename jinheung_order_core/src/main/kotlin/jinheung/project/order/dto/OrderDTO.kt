package jinheung.project.order.dto

import jinheung.project.event_common.dto.EventCommonDTO
import jinheung.project.order.entity.Address
import jinheung.project.order.entity.Order
import jinheung.project.order.entity.OrderProduct
import java.math.BigDecimal
import kotlin.streams.toList

class OrderDTO(
    val orderProducts : List<OrderProductDTO>,
    val address: Address = Address(),
    val requestMemo : String = "",
    override val eventId :String = "",
    override val listenerId: String = "",
    override val eventType: String = ""
) : EventCommonDTO() {
    data class OrderProductDTO(
        val productId : Long = 0L,
        val quantity : Int = 1,
        val price : BigDecimal = BigDecimal.valueOf(1)
    ) {
        companion object {
            fun fromEntity(orderProduct : OrderProduct) : OrderProductDTO {
                return OrderProductDTO(orderProduct.productId, orderProduct.quantity, orderProduct.price)
            }
        }
    }

    companion object {
        fun fromEntity(order: Order, orderProducts: List<OrderProduct>) : OrderDTO {
            return OrderDTO(
                orderProducts.stream().map { orderProduct -> OrderProductDTO.fromEntity(orderProduct) }.toList(),
                order.address,
                order.requestMemo
            )
        }
    }
}