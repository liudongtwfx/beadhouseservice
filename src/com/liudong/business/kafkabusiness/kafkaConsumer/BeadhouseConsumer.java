package com.liudong.business.kafkabusiness.kafkaConsumer;

import com.liudong.business.elasticsearchbusiness.ElasticSearchBuider;
import com.liudong.business.elasticsearchbusiness.ElasticsearchBase;
import com.liudong.business.kafkabusiness.kafkaconfig.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class BeadhouseConsumer implements Runnable {
    private final static Logger KAFKA_LOGGER = LogManager.getLogger("kafka");
    private final static Logger EXCEPTION_LOGGER = LogManager.getLogger("exception");
    private KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConsumerConfig.getInstance().getProperties());
    private ConsumerRecords<String, String> records;

    public BeadhouseConsumer(String... topic) {
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (true) {
                records = consumer.poll(1000);
                for (ConsumerRecord record : records) {
                    String key = (String) record.key();
                    String value = (String) record.value();
                    KAFKA_LOGGER.info(value);
                    processRecord(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private void processRecord(String key, String value) {
        System.out.println("liudong is here, " + value);
        String[] splitKey = key.split("\\|\\|");
        StringBuilder info = new StringBuilder();
        for (String k : splitKey) {
            info.append(k).append(" ");
        }
        if (splitKey[0].equals("es") && splitKey.length >= 3) {
            processRecordToEs(splitKey[1], splitKey[2], value);
        } else if (splitKey[0].equals("db") && splitKey.length >= 2) {
            processRecordToDb(splitKey[1], value);
        }
    }

    private void processRecordToDb(String tableName, String value) {

    }

    private void processRecordToEs(String index, String type, String value) {
        ElasticsearchBase es;
        try {
            es = ElasticSearchBuider.buildElasticsearchInstance(index, type);
            KAFKA_LOGGER.info(es == null);
            es.insertToEs(value);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            EXCEPTION_LOGGER.error(e.getMessage());
        }
    }

    public ConsumerRecords<String, String> getRecords() {
        return records;
    }
}
