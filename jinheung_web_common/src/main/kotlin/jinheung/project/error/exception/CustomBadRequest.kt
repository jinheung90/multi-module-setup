package jinheung.project.error.exception

import jinheung.project.error.enums.GlobalErrorCode

class CustomBadRequest(private var code: GlobalErrorCode, customMessage: String?) : RuntimeException(customMessage) {

    override val message : String = ""

    fun getCode() : GlobalErrorCode {
        return this.code
    }
}