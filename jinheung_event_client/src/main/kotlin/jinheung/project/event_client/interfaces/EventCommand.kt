package jinheung.project.event_client.interfaces


interface EventCommand<T> {
    fun tryCommand(t: T) : T
    fun cancelCommand(t: T)
    fun recoveryCommand(t: T) : T
}