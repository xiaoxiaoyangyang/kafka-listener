package com.sensetime.fis.senseguard.opendoor.consumer;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/6 19:03
 * @Version 1.0
 */
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * Kafka configuration.
 *
 * @author lizhengguang_vendor
 * @date 2019-1-3 16:40:28
 */
@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value(value = "${kafka.bootstrap-servers}")
    private String bootstrapServersConfig;

    @Value(value = "${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value(value = "${kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    @Value(value = "${kafka.consumer.auto-commit-interval}")
    private Integer autoCommitInterval;

    @Value(value = "${kafka.consumer.session-timeout-ms-config}")
    private Integer sessionTimeoutMsConfig;

    @Value(value = "${kafka.consumer.key-deserializer}")
    private String keyDeserializerClassConfig;

    @Value(value = "${kafka.consumer.value-deserializer}")
    private String valueDeserializerClassConfig;


    /** ConcurrentKafkaListenerContainerFactory */
    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
        log.info("ConcurrentKafkaListenerContainerFactory isOK!");
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumeFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    /** ConsumerFactory */
    @Bean
    public ConsumerFactory<Integer, String> consumeFactory() {
        log.info("ConsumerFactory isOK!");
        return new DefaultKafkaConsumerFactory<>(consumeConfigs());
    }

    /** consumeConfigs */
    @Bean
    public Map<String, Object> consumeConfigs() {
        Map<String, Object> props = new HashMap<>(16);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitInterval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMsConfig);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClassConfig);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClassConfig);
        return props;
    }
}

