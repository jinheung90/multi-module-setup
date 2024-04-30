package jinheung.project.mall.controller

import jinheung.project.mall.dto.MallDTO


import jinheung.project.mall.service.MallService
import jinheung.project.util.CustomSecuritySupport
import org.springframework.beans.factory.annotation.Value

import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/mall")
class MallController(
    private val mallService: MallService,

) {
    @Value(value =  "\${profile}")
    private val profile : String = "null"

    @PostMapping
    fun registerMall(@RequestBody mallDTO: MallDTO) : ResponseEntity<MallDTO> {
        val userId = CustomSecuritySupport.getUserId()
        val result = mallService.registerMall(userId, mallDTO.name)
        return ResponseEntity.ok(result)
    }
}