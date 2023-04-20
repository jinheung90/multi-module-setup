package jinheung.project.event_client.kafka.config

import jinheung.project.event_common.enums.EventTopicType
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import kotlin.streams.toList


@Configuration
class KafkaTopicConfig {

    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private val bootstrapAddress: String = ""

//    @Bean
//    fun producerFactory(): ProducerFactory<String, String> {
//        val configProps: MutableMap<String, Any> = HashMap()
//        val listKafkaServer = bootstrapAddress.split(',')
//        listKafkaServer.stream().forEach {
//                t -> println(t)
//        }
//        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = listKafkaServer
//
//        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
//        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
//        return DefaultKafkaProducerFactory(configProps)
//    }
//
//    @Bean
//    fun kafkaTemplate(): KafkaTemplate<String, String> {
//        return KafkaTemplate(producerFactory())
//    }

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val listKafkaServer = bootstrapAddress.split(',')
        listKafkaServer.stream().forEach {
                t -> println(t)
        }
        val configs: MutableMap<String, Any?> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = listKafkaServer

        val kafkaAdmin = KafkaAdmin(configs)
        val topic = EventTopicType.values().asList().stream().map {
                t -> NewTopic(t.topicName, 1, 1)
        }.toList().toTypedArray()
        kafkaAdmin.createOrModifyTopics(*topic)
        return kafkaAdmin
    }
}