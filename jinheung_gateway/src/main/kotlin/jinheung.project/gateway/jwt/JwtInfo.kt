package jinheung.project.gateway.jwt

import java.util.Date

data class JwtInfo(val userId : Long, val authorities : String,val expiration : Date)