package com.liudong.business.kafkabusiness.kafkaProducer;

import com.liudong.business.kafkabusiness.kafkaconfig.KafkaProducerConfig;
import org.apache.kafka.clients.producer.*;

public class BeadhouseCommentProducer {
    class BeadhouseCommentCallback implements Callback {
        private int beadhouseid;
        private String value;
        private long start;

        BeadhouseCommentCallback(int beadhouseid, String value) {
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

    public Producer<String, String> getProducer() {
        return producer;
    }

    private final Producer<String, String> producer = new KafkaProducer<>(KafkaProducerConfig.getInstance().getProperties());
    private static final String topic = "beadhousecomment";


    public void produceMessage(int beadhouseid, String value) {
        this.producer.send(new ProducerRecord<>(topic, String.valueOf(beadhouseid), value),
                new BeadhouseCommentCallback(beadhouseid, value));
    }
}
