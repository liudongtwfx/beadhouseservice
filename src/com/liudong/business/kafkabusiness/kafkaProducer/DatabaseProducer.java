package com.liudong.business.kafkabusiness.kafkaProducer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class DatabaseProducer {
    private Producer<String, String> producer;

    public DatabaseProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());
    }

    public void produceMyMessage(String topic) {
        try {
            for (int i = 0; i < 100; i++) {
                Future<RecordMetadata> metadataFuture = producer.send(new ProducerRecord<>(topic, String.valueOf(i), String.valueOf(i)),
                        new DemoCallBack(System.currentTimeMillis(), i, String.valueOf(i)));
                System.out.println(metadataFuture.get());
            }
        } catch (KafkaException e) {
            System.out.print(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            producer.close();
        }
    }

    public Producer<String, String> getProducer() {
        return producer;
    }
}


class DemoCallBack implements Callback {

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elaspsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println("message(" + key + ", " + message + ") sent to partition(" + metadata.partition() + ") " + "offset("
                    + metadata.offset() + ") in " + elaspsedTime + "ms");
        } else {
            exception.printStackTrace();
        }
    }
}

