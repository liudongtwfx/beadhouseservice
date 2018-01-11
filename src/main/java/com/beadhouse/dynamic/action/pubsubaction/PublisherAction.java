package main.java.com.beadhouse.dynamic.action.pubsubaction;

import main.java.com.beadhouse.business.kafkabusiness.kafkaconfig.KafkaProducerConfig;
import main.java.com.beadhouse.dynamic.action.AbstractActionImpl;
import main.java.com.beadhouse.dynamic.datawrapper.ProducerData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Map;

public class PublisherAction extends AbstractActionImpl {
    private ProducerData publisher;
    private final Producer<String, String> producer = new KafkaProducer<>(KafkaProducerConfig.getInstance().getProperties());

    public PublisherAction(ProducerData producerData) {
        this.publisher = producerData;
    }


    @Override
    protected void getSavingValue(Map<String, Object> value) {

    }
}
