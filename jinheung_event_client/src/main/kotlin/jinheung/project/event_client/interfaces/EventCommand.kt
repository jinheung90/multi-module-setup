package jinheung.project.event_client.interfaces

import jinheung.project.event_common.dto.EventCommonDTO


interface EventCommand<T: EventCommonDTO> {
    fun tryCommand(t: T) : T
    fun cancelCommand(t: T)
    fun recoveryCommand(t: T) : T
}