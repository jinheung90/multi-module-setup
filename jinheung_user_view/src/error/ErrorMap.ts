import { ErrorCodeMap} from "./ErrorCode";

export class ErrorMap extends ErrorCodeMap {
    constructor() {
        super()
        ErrorCodeMap.setErrorCode("0001", 400, false, "유저 이메일이 없습니다")
        ErrorCodeMap.setErrorCode("0002", 401, true, "유저 이메일이나 패스워드가 다릅니다")
    }
}