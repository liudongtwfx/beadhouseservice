package com.liudong.business.kafkabusiness.kafkaConsumer;

import com.liudong.business.kafkabusiness.kafkaconfig.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

public class BeadhouseConsumer implements Runnable {
    private KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConsumerConfig.getInstance().getProperties());
    private ConsumerRecords<String, String> records;

    public BeadhouseConsumer(String... topic) {
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (true) {
                records = consumer.poll(200);
                for (ConsumerRecord record : records) {
                    System.out.println(record.key() + " " + record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public ConsumerRecords<String, String> getRecords() {
        //records = consumer.poll(200);
        return records;
    }
}
