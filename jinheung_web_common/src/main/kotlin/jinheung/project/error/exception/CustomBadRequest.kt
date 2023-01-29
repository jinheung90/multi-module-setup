package jinheung.project.error.exception

import jinheung.project.error.enums.GlobalErrorCode

class CustomBadRequest : RuntimeException {

    private var code : GlobalErrorCode = GlobalErrorCode.BAD_REQUEST
    override val message : String = ""
    constructor(message: String, ex: Exception?): super(message, ex) {}
    constructor(message: String): super(message) {}
    constructor(ex: Exception): super(ex) {}
    constructor(code: GlobalErrorCode) : super(code.getMessage()) {
        this.code = code
    }

    constructor(code: GlobalErrorCode, customMessage: String?) : super(customMessage) {
        this.code = code
    }

    fun getCode() : GlobalErrorCode {
        return this.code
    }
}