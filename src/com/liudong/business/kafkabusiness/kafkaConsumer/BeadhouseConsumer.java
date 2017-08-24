package com.liudong.business.kafkabusiness.kafkaConsumer;

import com.liudong.business.kafkabusiness.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

public class BeadhouseConsumer implements Runnable {
    private KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConfig.getProperties());
    private ConsumerRecords<String, String> records;

    public BeadhouseConsumer(String... topic) {
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (true) {
                records = consumer.poll(200);
            }
        } finally {
            consumer.close();
        }
    }

    public ConsumerRecords<String, String> getRecords() {
        return records;
    }
}
