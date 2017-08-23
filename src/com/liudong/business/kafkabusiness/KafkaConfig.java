package com.liudong.business.kafkabusiness;

import java.util.Properties;

public class KafkaConfig {
    private static Properties props;
    private volatile static KafkaConfig instance = null;

    private KafkaConfig() {
        if (instance == null) {
            synchronized (KafkaConfig.class) {
                if (instance == null) {
                    instance = new KafkaConfig();
                    props.put("bootstrap.servers", "localhost:9092");
                    props.put("acks", "all");
                    props.put("retries", 0);
                    props.put("batch.size", 16384);
                    props.put("linger.ms", 1);
                    props.put("buffer.memory", 33554432);
                    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                }
            }

        }
    }

    public static Properties getProperties() {
        return props;
    }
}
