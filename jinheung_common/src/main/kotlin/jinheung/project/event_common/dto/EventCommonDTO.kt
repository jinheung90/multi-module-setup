package jinheung.project.event_common.dto

import java.math.BigDecimal


abstract class EventCommonDTO  {
    abstract val listenerId : String
    abstract val eventId : String
    abstract val eventType: String
}


