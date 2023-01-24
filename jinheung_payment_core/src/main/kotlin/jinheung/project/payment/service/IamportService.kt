package jinheung.project.payment.service

import com.siot.IamportRestClient.IamportClient
import com.siot.IamportRestClient.exception.IamportResponseException
import com.siot.IamportRestClient.request.CancelData
import com.siot.IamportRestClient.response.IamportResponse
import com.siot.IamportRestClient.response.Payment
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.math.BigDecimal
import javax.annotation.PostConstruct
import javax.transaction.Transactional

@Service
class IamportService {

    @Value("\${pay.iamport.rest-key}")
    private val restKey: String? = null

    @Value("\${pay.iamport.rest-secret}")
    private val secretKey: String? = null

    private var iamportClient: IamportClient? = null

    @PostConstruct
    fun initImportClient() {
        iamportClient = IamportClient(restKey, secretKey)
    }

    @Transactional
    fun verifyPayment(userId: Long?, productId: Long?, impUid: String?, quantity: Int?, price: Int?): Payment? {
        try {
            val paymentResponse: IamportResponse<Payment> = iamportClient!!.paymentByImpUid(impUid)
            return paymentResponse.response
        } catch (e: IamportResponseException) {
            when (e.httpStatusCode) {
                401 -> {}
                404 -> {}
                500 -> {}
            }
        } catch (e: IOException) {
        }
        return null
    }

    fun payCancel(impUid: String?, price: Int) {
        try {
            iamportClient?.cancelPaymentByImpUid(
                CancelData(impUid, true, BigDecimal.valueOf(price.toLong()))
            )
        } catch (e: IamportResponseException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}