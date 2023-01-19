package jinheung.project.axon.aggregate

import jinheung.project.event.ProductDTO
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ProductAggregate{
    @AggregateIdentifier
    private var productId : String? = null
    private var  productName : String? = null

    @EventSourcingHandler
    protected fun createProductEvent(productDTO: ProductDTO) {
        this.productId = productDTO.productId
        this.productName = productDTO.productName
    }
}

