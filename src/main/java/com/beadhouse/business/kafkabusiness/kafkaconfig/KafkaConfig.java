package main.java.com.beadhouse.business.kafkabusiness.kafkaconfig;

import java.util.Properties;

public abstract class KafkaConfig {
    protected final Properties props;

    public KafkaConfig() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
    }
}
