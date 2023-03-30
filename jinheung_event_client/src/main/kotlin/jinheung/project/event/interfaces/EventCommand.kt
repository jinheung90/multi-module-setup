package jinheung.project.event.dto.interfaces


interface EventCommand<T> {
    fun tryCommand(t : T)
    fun cancelCommand(t : T)
}