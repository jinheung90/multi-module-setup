package jinheung.project.error.enums

interface IErrorCode {
    fun getMessage(): String?
    fun getStatus(): Int
    fun getCode(): String?
}