package com.liudong.business.kafkabusiness.kafkaconfig;

import java.util.Properties;

public class KafkaConsumerConfig extends KafkaConfig {
    private static KafkaConsumerConfig instance = null;

    private KafkaConsumerConfig() {
        super();
        props.put("groupId", "test-consumer-group");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public static KafkaConsumerConfig getInstance() {
        if (instance == null) {
            synchronized (KafkaProducerConfig.class) {
                if (instance == null) {
                    instance = new KafkaConsumerConfig();
                }
            }
        }
        return instance;
    }

    public Properties getProperties() {
        System.out.println("consumer:" + props.getProperty("groupId"));
        return props;
    }
}
