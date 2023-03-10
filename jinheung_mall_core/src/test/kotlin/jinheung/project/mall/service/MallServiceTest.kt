package jinheung.project.mall.service

import jinheung.project.mall.service.MallService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@Tag("integration")
@ActiveProfiles("test")
class MallServiceTest  (
    @Autowired private val mallService: MallService
)  {
    @Test
    fun saveMall() {
        val value = mallService.registerMall(1L, "tester")
        Assertions.assertEquals(value.name, "tester")
    }
}