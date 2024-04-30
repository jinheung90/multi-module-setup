package jinheung.project.common

import jinheung.project.mall.repository.MallHasUserRepository
import jinheung.project.mall.repository.MallRepository
import jinheung.project.mall.service.MallService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@Tag("unit")
@ExtendWith(MockitoExtension::class)
class UnitTestBase(
    @Mock val mallService: MallService,
    @Mock val mallRepository : MallRepository,
    @Mock val mallHasUserRepository: MallHasUserRepository
) {
    @Test
    fun registerMall() {

    }
}