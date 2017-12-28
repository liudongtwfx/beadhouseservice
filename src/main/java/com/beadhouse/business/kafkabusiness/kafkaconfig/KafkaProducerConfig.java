package main.java.com.beadhouse.business.kafkabusiness.kafkaconfig;

import java.util.Properties;

public class KafkaProducerConfig extends KafkaConfig {
    private volatile static KafkaProducerConfig instance;

    private KafkaProducerConfig() {
        super();
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static KafkaProducerConfig getInstance() {
        if (instance == null) {
            synchronized (KafkaProducerConfig.class) {
                if (instance == null) {
                    instance = new KafkaProducerConfig();
                }
            }
        }
        return instance;
    }

    public Properties getProperties() {
        return props;
    }
}
