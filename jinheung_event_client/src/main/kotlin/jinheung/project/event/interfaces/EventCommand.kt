package jinheung.project.event.interfaces


interface EventCommand<T> {
    fun tryCommand(t : T) : String
    fun cancelCommand(t : T) : String
}