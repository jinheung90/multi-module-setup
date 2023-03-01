package jinheung.project.eureka.config

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig {
    @Bean
    fun awsCredentialsProvider() : AWSCredentialsProvider {
        return DefaultAWSCredentialsProviderChain();
    }


}