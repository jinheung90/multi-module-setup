package jinheung.project.mall.service


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@Tag("integration")
@ActiveProfiles("test")
class MallServiceIntegrationTest  (
    @Autowired private val mallService: MallService
)  {
    @Test
    @Transactional
    fun saveMall() {
        val value = mallService.registerMall(1L, "tester")
        Assertions.assertEquals("tester", value.name)
    }

    @Test
    fun saveMallFailTest() {
        val value = mallService.registerMall(2L, "tester2")
        Assertions.assertEquals("tester3", value.name)
    }
}