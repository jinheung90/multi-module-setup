package jinheung.project.payment.service


import com.siot.IamportRestClient.IamportClient
import com.siot.IamportRestClient.exception.IamportResponseException
import com.siot.IamportRestClient.request.CancelData
import com.siot.IamportRestClient.response.IamportResponse
import com.siot.IamportRestClient.response.Payment
import jinheung.project.payment.entity.CanceledLog
import jinheung.project.payment.entity.IamportLog
import jinheung.project.payment.repository.CanceledLogRepository
import jinheung.project.payment.repository.IamportLogRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.math.BigDecimal
import javax.annotation.PostConstruct
import javax.transaction.Transactional

@Service

class PaymentService(
    private val iamportLogRepository: IamportLogRepository,
    private val canceledLogRepository: CanceledLogRepository
) {

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
    fun saveIamportLog(
        userId: Long,
        merchantUid : String,
        impUid: String,
        price: BigDecimal,
        canceledLog: CanceledLog?
    ): IamportLog {
        return iamportLogRepository.save(
            IamportLog.of(
                impUid = impUid,
                price = price,
                canceledLog = canceledLog,
                merchantUid = merchantUid,
                userId = userId
            )
        )
    }
    @Transactional
    fun saveCanceledLog(statusCode : Int, message : String, refundMoney : BigDecimal) :CanceledLog {
        return canceledLogRepository.save(CanceledLog.of(statusCode, message, refundMoney))
    }

    @Transactional
    fun verifyPayment(userId: Long, merchantUid: String, impUid: String, productPrice: BigDecimal): Payment? {
        var canceledLog : CanceledLog? = null
        try {
            val paymentResponse = iamportClient?.paymentByImpUid(impUid)
            if (paymentResponse != null) {
                val payment = paymentResponse.response
                if(payment.amount != productPrice) {
                    canceledLog = CanceledLog.of(statusCode = 400, message = "productPrice not equal payPrice", refundMoney = payment.amount) //
                    this.payCancel(impUid, payment.amount)
                    throw RuntimeException("productPrice not equal payPrice")
                }
            }
        } catch (e: IamportResponseException) {
            var isImpUidExists = true
            var statusCode = 400
            when (e.httpStatusCode) {
                401 -> {
                    statusCode = 401
                }
                404 -> {
                    System.err.println("error")
                    isImpUidExists = false
                    statusCode = 404
                }
                500 -> {
                    statusCode = 500
                }
            }
            val payment = this.payCancel(impUid, productPrice)
            canceledLog = CanceledLog.of(statusCode = statusCode, message = e.localizedMessage, productPrice)
        } finally {
            saveIamportLog(userId = userId, merchantUid = merchantUid, impUid = impUid, price = productPrice, canceledLog = canceledLog)
        }
        return null
    }

    fun payCancel(impUid: String,  price: BigDecimal) {
        try {
            val response = iamportClient?.cancelPaymentByImpUid(CancelData(impUid, true, price))
        } catch (e: IamportResponseException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}