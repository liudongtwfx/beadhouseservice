package main.java.com.beadhouse.business.kafkabusiness.kafkaconfig;

import java.util.Properties;

public class KafkaConsumerConfig extends KafkaConfig {
    private static KafkaConsumerConfig instance = null;

    private KafkaConsumerConfig() {
        super();
        props.put("group.id", "test-consumer-group");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "10");
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
        return props;
    }
}
