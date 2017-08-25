package com.liudong.business.kafkabusiness.kafkaconfig;

import java.util.Properties;

public abstract class KafkaConfig {
    protected final Properties props;

    public KafkaConfig() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
    }
}
