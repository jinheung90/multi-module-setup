package jinheung.project.util

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


class CustomSecuritySupport {
    companion object  {

        fun getUserId() : Long {
            val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
            return req.getHeader(SecurityConst.getUserIdHeaderName()).toLong()
        }

        fun getUserAuthorities() : List<String> {
            val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
            return req.getHeader(SecurityConst.getAuthoritiesHeaderName()).split(",")
        }
    }
}