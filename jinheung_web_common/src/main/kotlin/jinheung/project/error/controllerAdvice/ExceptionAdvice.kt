package jinheung.project.error.controllerAdvice

import jinheung.project.error.ErrorResponse
import jinheung.project.error.exception.CustomBadRequest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import kotlin.collections.HashMap

@RestControllerAdvice
class ExceptionAdvice {
    // 내 커스텀 용 에러 핸들러
    @ExceptionHandler(CustomBadRequest::class)
    fun all(e: CustomBadRequest): ResponseEntity<HashMap<String, String>> {
        return ErrorResponse.response(
            e.getCode().getCode(),
            e.message,
            HttpStatus.resolve(e.getCode().getStatus())
        )
    }


    // request 잘못 됬을 때
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): Any? {
//        val errorMessage = e.bindingResult
//            .allErrors[0]
//            .defaultMessage
//        return ResponseEntity<HashMap<String, Any>>(object : HashMap<String?, Any?>() {
//            init {
//                put("message", "no validations")
//                put("error position", Objects.requireNonNull(e.fieldError).field)
//                put("CODE", "G001")
//            }
//        }, HttpStatus.BAD_REQUEST)
//    }
//
//
//    @ExceptionHandler(NumberFormatException::class)
//    fun handleNumFormatException(e: NumberFormatException): Any? {
//        val errorMessage = e.localizedMessage
//        return ResponseEntity<HashMap<String, Any>>(object : HashMap<String?, Any?>() {
//            init {
//                put("message", "no validations")
//                put("detail", errorMessage)
//                put("CODE", "G001")
//            }
//        }, HttpStatus.BAD_REQUEST)
//    }
}