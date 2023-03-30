package jinheung.project.event.kafka.listener


import jinheung.project.event.dto.interfaces.EventCommand
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class EventPub<T>(
    private val kafkaTemplate : KafkaTemplate<String, String>,
    private val eventCommand: EventCommand<T>
) {

//TODO 이벤트를 어떻게 모듈화 시켜서 공통모듈을 만들것인지.
//    fun tryPub(eventCommand : (t : T) -> String) {
//        kafkaTemplate.send()
//    }


    fun cancelListener() {

    }
}