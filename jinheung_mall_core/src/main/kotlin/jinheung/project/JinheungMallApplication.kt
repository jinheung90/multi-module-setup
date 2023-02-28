package jinheung.project

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class JinheungMallApplication
fun main(args: Array<String>) {
	@Bean
	fun awsCredentialsProvider() : AWSCredentialsProvider {
		return DefaultAWSCredentialsProviderChain();
	}
	runApplication<JinheungMallApplication>(*args)
}
