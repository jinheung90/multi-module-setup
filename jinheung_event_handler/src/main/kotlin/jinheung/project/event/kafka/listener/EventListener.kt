package jinheung.project.event.kafka.listener

import jinheung.project.event.dto.enums.KafkaCommandType
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class EventListener(
    private val kafkaTemplate : KafkaTemplate<String, String>
) {

    @KafkaListener(id = KafkaCommandType.ID, topics = [KafkaCommandType.TRY])
    fun tryListener() {
        //TODO save commit
    }

    @KafkaListener(id = KafkaCommandType.ID, topics = [KafkaCommandType.CANCEL])
    fun cancelListener() {
        //TODO findAllByEventID, and then sendCancelEvents
        // 보상체계 준비해야한다 전체 정보를 넘겨주고 cancel하도록 한다.
        // 고민했던 부분 보상 트랜잭션은 어떻게 하지?
        // 만약에 업데이트 했던 것을 되돌리는거면?
        // 생각을 해보면 단순 업데이트를 서버 간의 통신으로 내에서 호출해야할까? 라는 의문부터 시작해야한다.
        // 사가 패턴의 보상체계는 단순 업데이트가 아니라 서비스 흐름간의 트랜잭션이다. 비지니스적 로직을 보상하는것이다. 따라서 재고가 없으면 환불처리. 돈이 없으면 재고를 다시 업데이트 이런식인거다.
        // 따라서 단순히 재고가 없는걸 업데이트하려는건 api 호출하자

    }

    @KafkaListener(id = KafkaCommandType.ID, topics = [KafkaCommandType.RECOVERY])
    fun recoveryListener() {
        //TODO findById and update state

    }
}