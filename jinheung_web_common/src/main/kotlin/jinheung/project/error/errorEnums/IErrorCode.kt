package jinheung.project.error.errorEnums

interface IErrorCode {
    fun getMessage(): String?
    fun getStatus(): Int
    fun getCode(): String?
}