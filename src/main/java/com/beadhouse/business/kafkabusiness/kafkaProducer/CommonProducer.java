package main.java.com.beadhouse.business.kafkabusiness.kafkaProducer;

import main.java.com.beadhouse.business.kafkabusiness.kafkaconfig.KafkaProducerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonProducer {
    private final static Logger LOGGER = LogManager.getLogger("exception");
    private final static Logger KAFKA_LOGGER = LogManager.getLogger("kafka");

    class CommonCallback implements Callback {
        private String value;
        private long start;

        CommonCallback(String value) {
            this.value = value;
            this.start = System.currentTimeMillis();
        }

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            long elaspsedTime = System.currentTimeMillis() - start;
            if (metadata != null) {
                KAFKA_LOGGER.info("message(" + value + ") sent to partition(" + metadata.partition() + ") " + "offset("
                        + metadata.offset() + ") in " + elaspsedTime + "ms,topic:" + metadata.topic());
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

    public CommonProducer() {

    }

    public void produceMessage(String topic, String key, String value) {
        if (topic == null || topic.length() == 0) {
            throw new IllegalArgumentException("Kafka producer can not be null");
        }
        this.producer.send(new ProducerRecord<>(topic, key, value),
                new CommonCallback(value));
    }
}
