package jinheung.project.event_common.enums


enum class EventTopicType(val topicName: String) {
    ORDER("k-event-order");

    companion object {
        fun find(value :String) : EventTopicType {
            return EventTopicType.valueOf(value)
        }
    }
}

