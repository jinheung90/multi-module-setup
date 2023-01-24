export class ErrorCode {

    readonly customCode: String = ""
    readonly statusCode: Number = 0
    readonly isAlert : Boolean = false
    readonly message : String = ""

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

    protected static readonly map = new Map<String, ErrorCode>()
    constructor() {

    }

    protected static setErrorCode(
        customCode : String,
        statusCode: Number,
        isAlert : Boolean,
        message : String = ""
        ) : ErrorCode {
        let errorCode = new ErrorCode(customCode, statusCode, isAlert, message)
        this.map.set(customCode, errorCode)
        return errorCode
    }

    public static getErrorCode = (customCodeName : String) : ErrorCode => {
        let errorCode = ErrorCodeMap.map.get(customCodeName);
        if(!errorCode) {
            console.log("error code not exists")
            errorCode = this.map.get("0000")
            if(!errorCode) {
                errorCode = ErrorCodeMap.setErrorCode("0000", 400, false, "not exists code")
            } else {
                return errorCode
            }
        }
        return errorCode
    }
}