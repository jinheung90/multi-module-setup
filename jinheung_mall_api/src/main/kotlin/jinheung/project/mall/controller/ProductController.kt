package jinheung.project.mall.controller


import jinheung.project.mall.entity.Product


import jinheung.project.mall.service.ProductService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


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
        val results = productService.findAllProductsByMall(id);
        return ResponseEntity.ok().body(results)
    }
}