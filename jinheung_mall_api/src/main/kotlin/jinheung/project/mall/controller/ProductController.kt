package jinheung.project.mall.controller

import jinheung.project.mall.dto.MallDTO
import jinheung.project.mall.entity.Product
import jinheung.project.mall.enums.Category
import jinheung.project.mall.service.MallService
import jinheung.project.mall.service.ProductService
import jinheung.project.util.CustomSecuritySupport
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService,

) {
    @GetMapping("/mall/{id}/products")
    fun getProductsByMallIdAndCategory(
        @RequestParam(name = "category") category: String,
        @PathVariable(name = "id") id : Long
    ) : ResponseEntity<List<Product>> {
        val results = productService.findAllProductsByMall(id, Category.findCategory(category));
        return ResponseEntity.ok().body(results)
    }
}