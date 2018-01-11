package main.java.com.beadhouse.business.kafkabusiness.kafkaConsumer;

import main.java.com.beadhouse.business.kafkabusiness.kafkaconfig.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public abstract class AbstractConsumer {
    protected KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConsumerConfig.getInstance().getProperties());

    public abstract void consumeMessage(ConsumerRecords<String, String> records);
}
