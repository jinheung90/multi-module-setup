package jinheung.project.gateway.auth.filter


import jinheung.project.gateway.jwt.TokenProvider
import lombok.Getter
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ServerWebExchange
import jinheung.project.util.SecurityHeaders
//import jinheung.project.common
@Component
abstract class AuthFilterFactory(
    private val tokenProvider: TokenProvider
) : AbstractGatewayFilterFactory<AuthFilterFactory?>() {



    @Getter
    class Config

    private fun resolveToken(headerVal: List<String>?): String? {
        if ((headerVal == null) || headerVal.isEmpty()) {
            return null
        }
        val strToken = headerVal[0]
        return if (StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")) {
            strToken.substring(7)
        } else null
    }

    override fun apply(config: AuthFilterFactory?): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val headers = request.headers
            val headerVal = headers[HttpHeaders.AUTHORIZATION]
            val token = resolveToken(headerVal)
            val tokenInfo = tokenProvider.getUserIdAndAuthorityByJwtAccessToken(token)
            if (tokenInfo.userId != 0L) {
                request.mutate().header(SecurityHeaders.USER_AUTHORITIES_HEADER_NAME, tokenInfo.authorities).build()
                request.mutate().header(SecurityHeaders.USER_ID_HEADER_NAME, tokenInfo.userId.toString()).build()
            }
            chain.filter(exchange.mutate().request(request).build())
        }
    }
}