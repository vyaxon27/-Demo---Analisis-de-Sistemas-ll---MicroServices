package com.umg.analisis.sistemas.config;

import java.util.HashMap;
import java.util.Map;

import com.umg.analisis.sistemas.payload.CustomerPayload;
import com.umg.analisis.sistemas.constants.KafkaGroupConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerPayload> listenerContainerFactory() {
        return listenerContainerFactory(KafkaGroupConstants.GROUP);
    }

    private ConsumerFactory<String, CustomerPayload> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15 * 1000);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(CustomerPayload.class));
    }

    private ConcurrentKafkaListenerContainerFactory<String, CustomerPayload> listenerContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, CustomerPayload> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId));
        return factory;
    }

}
