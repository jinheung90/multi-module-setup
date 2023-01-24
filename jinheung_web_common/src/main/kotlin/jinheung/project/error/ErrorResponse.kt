package jinheung.project.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ErrorResponse {
    fun response(code: String, message: String, status: HttpStatus?): ResponseEntity<HashMap<String, String>> {
        return ResponseEntity
            .status(status!!)
            .body(responseBody(code, message))
    }

    private fun responseBody(code: String, message: String): HashMap<String, String> {
        val body = HashMap<String, String>()
        body["message"] = message
        body["CODE"] = code
        return body
    }
}
