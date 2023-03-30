package jinheung.project.event.dto.enums

import jinheung.project.util.SecurityConst

object KafkaCommandType {
    const val ID = "event-command"
    const val TRY = "k-try-command"
    const val CANCEL = "k-cancel-command"
    const val RECOVERY = "k-recovery-command"
}