export class ErrorCode {

    private readonly customCode: String = ""
    private readonly statusCode: Number = 0
    private readonly isAlert : Boolean = false
    private readonly message : String = ""
    constructor(
        customCode: String,
        statusCode: Number,
        isAlert : Boolean,
        message : String = ""
    ) {
        this.customCode = customCode
        this.statusCode = statusCode
        this.isAlert = isAlert
        this.message = message
    }
}

export class ErrorCodeMap {

    protected readonly map = new Map<String, ErrorCode>()
    constructor() {

    }

    protected setErrorCode(
        customCode : String,
        statusCode: Number,
        isAlert : Boolean,
        message : String = ""
        ) {
        this.map.set(customCode, new ErrorCode(customCode, statusCode, isAlert, message))
    }
}