package com.umg.analisis.sistemas.consumer;

import com.umg.analisis.sistemas.constants.KafkaTopicConstants;
import com.umg.analisis.sistemas.payload.CustomerPayload;
import com.umg.analisis.sistemas.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CustomerService customerService;

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaTopicConstants.CUSTOMER_CREATE, partitions =
            {"0"}), containerFactory = "listenerContainerFactory")
    public void consume(@Payload ConsumerRecord<String, CustomerPayload> record) {
        log.info("user received -> record: {}", record);
        customerService.createAndSend(record.value());
    }

}
