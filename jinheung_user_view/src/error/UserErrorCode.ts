import {ErrorCode, ErrorCodeMap} from "./ErrorCode";

class UserErrorCode extends ErrorCodeMap {
    constructor() {
        super();
        this.setErrorCode("0001", 400, false, "유저 이메일이 없습니다")
        this.setErrorCode("0002", 401, true, "유저 이메일이나 패스워드가 다릅니다")
    }
}