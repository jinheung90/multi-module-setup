package jinheung.project.util

//import org.springframework.web.context.request.RequestContextHolder
//import org.springframework.web.context.request.ServletRequestAttributes


class SecurityConst {
    companion object  {
        private const val USER_ID_HEADER : String = "x-user-id"
        private const val USER_AUTHORITIES_HEADER : String = "x-user-authorities"
//        fun getUserId() : Long {
//            val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
//            return req.getHeader(USER_ID_HEADER).toLong()
//        }
//
//        fun getUserAuthorities() : List<String> {
//            val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
//            return req.getHeader(USER_AUTHORITIES_HEADER).split(",")
//        }
        fun getUserIdHeaderName() : String {
            return USER_ID_HEADER
        }

        fun getAuthoritiesHeaderName() : String {
            return USER_ID_HEADER
        }
    }
}