package jinheung.project.event_common.dto

import java.math.BigDecimal


data class EventCommonDTO<T> (
    val listenerId : String,
    val eventId : String,
    val data: T,
    val eventType: String)

