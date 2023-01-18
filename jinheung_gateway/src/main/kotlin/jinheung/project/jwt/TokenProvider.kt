package jinheung.project.jwt


import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException


import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.util.*


@Component
class TokenProvider : InitializingBean {
    @Value("\${token.access-token-secret}")
    private val secret: String? = null

    @Value("\${token.access-token-expired}")
    private val mAccessTokenExpiration: Long? = null
    private var key: Key? = null
    override fun afterPropertiesSet() {
        val keyBytes: ByteArray = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun getUserIdAndAuthorityByJwtAccessToken(token: String?): JwtInfo {
        return try {
            val claims: Claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
//            val authorities = Arrays.stream<String>(
//                claims.get(AUTHORITIES_KEY).toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }
//                    .toTypedArray())
//                .collect(Collectors.toList())
            JwtInfo(
                java.lang.Long.valueOf(claims.subject),
                claims[AUTHORITIES_KEY].toString(),
                claims.expiration
            )
        } catch (e: SecurityException) {

            return JwtInfo(0, "", Date.from(Instant.now()))
        } catch (e: MalformedJwtException) {

            return JwtInfo(0, "", Date.from(Instant.now()))
        } catch (e: UnsupportedJwtException) {

            return JwtInfo(0, "", Date.from(Instant.now()))
        } catch (e: IllegalArgumentException) {
            return JwtInfo(0, "", Date.from(Instant.now()))

        }
    }

    fun createJwtAccessTokenByUser(userId : Long, authorities: List<String>): String {
        val authorities: String = authorities.joinToString(separator = ",")
        val validity = Date(Date().time + mAccessTokenExpiration!! * 1000)
        return Jwts.builder()
            .setSubject(userId.toString())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact()
    }

    companion object {
        private const val AUTHORITIES_KEY = "Authorities"
    }
}