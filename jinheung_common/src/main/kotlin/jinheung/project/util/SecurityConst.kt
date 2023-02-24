package jinheung.project.util

//import org.springframework.web.context.request.RequestContextHolder
//import org.springframework.web.context.request.ServletRequestAttributes


class SecurityConst {
    companion object  {
        private const val USER_ID_HEADER : String = "x-user-id"
        private const val USER_AUTHORITIES_HEADER : String = "x-user-authorities"
        fun getUserIdHeaderName() : String {
            return USER_ID_HEADER
        }

        fun getAuthoritiesHeaderName() : String {
            return USER_ID_HEADER
        }
    }
}