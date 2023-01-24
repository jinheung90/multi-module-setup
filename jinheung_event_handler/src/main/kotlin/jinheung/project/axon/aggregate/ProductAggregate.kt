package jinheung.project.axon.aggregate

import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.stereotype.Component

//@Aggregate
//class ProductAggregate() {
//    @AggregateIdentifier
//    private var productId : String? = null
//    private var  productName : String? = null
//
//    @EventSourcingHandler
//    protected fun createProductEvent(productDTO: ProductDTO) {
//        this.productId = productDTO.productId
//        this.productName = productDTO.productName
//    }
//}
//
