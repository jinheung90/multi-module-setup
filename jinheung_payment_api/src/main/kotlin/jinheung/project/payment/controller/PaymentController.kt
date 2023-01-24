package jinheung.project.payment.controller


import jinheung.project.payment.service.IamportService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment/iamport")
class PaymentController(
    private val iamportService: IamportService
) {


}