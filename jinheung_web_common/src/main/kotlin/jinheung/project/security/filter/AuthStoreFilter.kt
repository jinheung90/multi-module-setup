package jinheung.project.security.filter

import jakarta.servlet.ServletException


import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import jakarta.servlet.FilterChain

import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jinheung.project.util.SecurityConst

@Component
class AuthStoreFilter : GenericFilterBean() {

    @Value("secure-header-value")
    private val secureHeaderValue : String = "";

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse?, filterChain: FilterChain) {
        val httpServletRequest = servletRequest as HttpServletRequest
//        println(httpServletRequest.getHeader(SecurityConst.getSecureHeaderName()))
//        println(secureHeaderValue)
//        if (secureHeaderValue != httpServletRequest.getHeader(SecurityConst.getSecureHeaderName())) {
//            throw CustomBadRequest(GlobalErrorCode.NOT_VALID_SECURE_HEADER, GlobalErrorCode.NOT_VALID_SECURE_HEADER.getMessage())
//        }

        val userId = httpServletRequest.getHeader(SecurityConst.getUserIdHeaderName());
        val authorityString = httpServletRequest.getHeader(SecurityConst.getAuthoritiesHeaderName());
        if(!(userId.isNullOrBlank() || authorityString.isNullOrBlank())) {
            this.setAuthentication(userId.toLong(), authorityString)
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }
    private fun setAuthentication(userId : Long, authority: String) {
        val authorities = Arrays.stream(
            authority.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray())
                .map {
                    a -> SimpleGrantedAuthority(a)
                }
                .collect(Collectors.toList())
        val principal = User(userId.toString(), "", authorities)
        val authentication: Authentication =
            UsernamePasswordAuthenticationToken(principal, "", authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }
}