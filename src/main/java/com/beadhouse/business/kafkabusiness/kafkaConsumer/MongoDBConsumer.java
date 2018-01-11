package main.java.com.beadhouse.business.kafkabusiness.kafkaConsumer;

import main.java.com.beadhouse.business.mongodbbusiness.SaveToMongoDB;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;

public class MongoDBConsumer extends AbstractConsumer implements Runnable {
    public MongoDBConsumer() {
        consumer.subscribe(Arrays.asList("MongoDB"));
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                consumeMessage(records);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consumeMessage(ConsumerRecords<String, String> records) {
        SaveToMongoDB saveToMongoDB = new SaveToMongoDB();
        try {
            for (ConsumerRecord<String, String> record : records) {
                saveToMongoDB.insertToMongoDB(record.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            saveToMongoDB.close();
        }
    }
}
