package jinheung.project.auth.filter


import jinheung.project.jwt.TokenProvider
import jinheung.project.util.SecurityConst
import org.springframework.beans.factory.annotation.Value

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ServerWebExchange
import kotlin.math.log


@Component
class AuthFilterFactory (
    private val tokenProvider: TokenProvider
) : AbstractGatewayFilterFactory<AuthFilterFactory.Config>() {

    @Value("secure-header-value")
    private val secureHeaderValue : String = "";

    class Config

    private fun resolveToken(headerVal: List<String>?): String? {
        if (headerVal.isNullOrEmpty()) {
            return null
        }
        val strToken = headerVal[0]
        return if (StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")) {
            strToken.substring(7)
        } else null
    }

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->

            val request = exchange.request
            val headers = request.headers
            val headerVal = headers[HttpHeaders.AUTHORIZATION]
            val token = resolveToken(headerVal)

            if(!token.isNullOrBlank()) {
                val tokenInfo = tokenProvider.getUserIdAndAuthorityByJwtAccessToken(token)
                if (tokenInfo.userId != 0L) {
                    request.mutate().header(SecurityConst.getAuthoritiesHeaderName(), tokenInfo.authorities).build()
                    request.mutate().header(SecurityConst.getUserIdHeaderName(), tokenInfo.userId.toString()).build()
                    request.mutate().header(SecurityConst.getSecureHeaderName(), secureHeaderValue)
                }
            }

            chain.filter(exchange.mutate().request(request).build())
        }
    }
}