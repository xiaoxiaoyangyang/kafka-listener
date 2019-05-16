package com.sensetime.fis.senseguard.opendoor.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 18:06
 * @Version 1.0
 */
@Configuration
@Slf4j
public class KafkaConsumer {
    /**
     * 监听
     * @param record
     */
    @KafkaListener(topics = {"123123"},groupId = "${abc}")
    public void receiveMessage(ConsumerRecord<?, ?> record) throws IOException {

    }

}
