package jinheung.project.mall.service

import jinheung.project.error.exception.CustomBadRequest
import jinheung.project.mall.enums.Category
import jinheung.project.mall.service.MallService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.test.context.ActiveProfiles


@Tag("unit")
class MallServiceTest  (
)  {
    @Test
    fun checkEnumSuccessTest() {
        Assertions.assertThrows(CustomBadRequest::class.java) { Category.findCategory("test") };
    }

    @Test
    fun saveMallFailTest() {
        Assertions.assertThrows(CustomBadRequest::class.java) { Category.findCategory(Category.FOOD.name) };
    }
}