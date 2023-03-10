package jinheung.project.util

//import org.springframework.web.context.request.RequestContextHolder
//import org.springframework.web.context.request.ServletRequestAttributes


class SecurityConst {
    companion object  {
        private const val USER_ID_HEADER : String = "x-user-id"
        private const val USER_AUTHORITIES_HEADER : String = "x-user-authorities"
        private const val SECURE_HEADER : String = "x-secure"
        fun getUserIdHeaderName() : String {
            return USER_ID_HEADER
        }

        fun getAuthoritiesHeaderName() : String {
            return USER_AUTHORITIES_HEADER
        }
        fun getSecureHeaderName() : String {
            return SECURE_HEADER
        }
    }
}