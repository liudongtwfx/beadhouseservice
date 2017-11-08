package main.java.com.beadhouse.business.kafkabusiness.kafkaProducer;

import main.java.com.beadhouse.business.kafkabusiness.kafkaconfig.KafkaProducerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeadhouseCommentProducer {
    private final static Logger LOGGER = LogManager.getLogger("exception");
    private final static Logger KAFKA_LOGGER = LogManager.getLogger("kafka");

    class BeadhouseCommentCallback implements Callback {
        private String value;
        private long start;

        BeadhouseCommentCallback(String value) {
            this.value = value;
            this.start = System.currentTimeMillis();
        }

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            long elaspsedTime = System.currentTimeMillis() - start;
            if (metadata != null) {
                KAFKA_LOGGER.info("message(" + value + ") sent to partition(" + metadata.partition() + ") " + "offset("
                        + metadata.offset() + ") in " + elaspsedTime + "ms");
            } else {
                exception.printStackTrace();
                LOGGER.error(exception.getMessage());
            }
        }
    }

    public Producer<String, String> getProducer() {
        return producer;
    }

    private final Producer<String, String> producer = new KafkaProducer<>(KafkaProducerConfig.getInstance().getProperties());
    private static final String topic = "beadhousecomment";

    public BeadhouseCommentProducer() {

    }

    public void produceMessage(String key, String value) {
        this.producer.send(new ProducerRecord<>(topic, key, value),
                new BeadhouseCommentCallback(value));
    }
}
