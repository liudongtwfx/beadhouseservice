package com.liudong.business.kafkabusiness.kafkaProducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class BeadhouseCommentProducer {
    class BeadhouseCommentCallback implements Callback {
        private int beadhouseid;
        private String value;
        private long start;

        public BeadhouseCommentCallback(int beadhouseid, String value) {
            this.beadhouseid = beadhouseid;
            this.value = value;
            this.start = System.currentTimeMillis();
        }

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            long elaspsedTime = System.currentTimeMillis() - start;
            if (metadata != null) {
                System.out.println("message(" + String.valueOf(beadhouseid) + ", " + value + ") sent to partition(" + metadata.partition() + ") " + "offset("
                        + metadata.offset() + ") in " + elaspsedTime + "ms");
            } else {
                exception.printStackTrace();
            }
        }
    }

    private final DatabaseProducer databaseProducer = new DatabaseProducer();
    private static final String topic = "beadhousecomment";


    public void produceMessage(int beadhouseid, String value) {
        this.databaseProducer.getProducer().send(new ProducerRecord<>(topic, String.valueOf(beadhouseid), value),
                new BeadhouseCommentCallback(beadhouseid, value));
    }
}
