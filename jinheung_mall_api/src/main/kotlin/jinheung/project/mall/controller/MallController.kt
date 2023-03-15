package jinheung.project.mall.controller

import jinheung.project.mall.dto.MallDTO
import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import jinheung.project.mall.service.MallService
import jinheung.project.util.CustomSecuritySupport
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/mall")
class MallController(
    private val mallService: MallService,

) {
    @Value(value =  "\${profile}")
    private val profile : String = "null"

    @PostMapping
    fun registerMall(@RequestBody @Valid mallDTO: MallDTO) : ResponseEntity<MallDTO> {
        val userId = CustomSecuritySupport.getUserId()
        val result = mallService.registerMall(userId, mallDTO.name)
        return ResponseEntity.ok(result)
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/test")
    fun test() : ResponseEntity<String> {
        return ResponseEntity.ok("CustomSecuritySupport.getUserAuthorities()")
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    fun test2() : ResponseEntity<String> {
        return ResponseEntity.ok("test")
    }
}