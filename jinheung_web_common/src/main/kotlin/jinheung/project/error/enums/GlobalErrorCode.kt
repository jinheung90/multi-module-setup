package jinheung.project.error.enums

enum class GlobalErrorCode(
    private val message: String,
    private val code : String,
    private val status : Int
    ) {

    BAD_REQUEST("bad request", "0000",400),
    SEND_FAIL("not connected child server","0010", 500),
    ASYNC_FAIL("ASYNC_FAIL", "0011",500),
    SQL_ERROR("SQL_ERROR", "0012",500),
    IO_ERROR("io error", "0013",500),
    NOT_EXISTS_USER("not exists user", "0001",401),
    NOT_VALID_TOKEN("not valid access token", "0005",403),
    ;


     fun getMessage(): String {
        // TODO Auto-generated method stub
        return this.message
    }

     fun getCode(): String {
        // TODO Auto-generated method stub
        return code
    }

     fun getStatus(): Int {
        return this.status
    }
}