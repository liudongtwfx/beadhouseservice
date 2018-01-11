package main.java.com.beadhouse.business.kafkabusiness.kafkaConsumer;

import com.google.gson.Gson;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.elasticsearchbusiness.ElasticSearchBuider;
import main.java.com.beadhouse.business.elasticsearchbusiness.ElasticsearchBase;
import main.java.com.beadhouse.business.kafkabusiness.kafkaconfig.KafkaConsumerConfig;
import main.java.com.beadhouse.model.beadhouse.BeadhouseInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Service
public class BeadhouseInfoConsumer implements Runnable {
    private final static Logger KAFKA_LOGGER = LogManager.getLogger("kafka");
    private final static Logger EXCEPTION_LOGGER = LogManager.getLogger("exception");
    private KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConsumerConfig.getInstance().getProperties());
    private ConsumerRecords<String, String> records;
    @Inject
    BeadhouseInfoRepository beadhouseInfoRepository;

    @Override
    public void run() {
        this.consumer.subscribe(Collections.singletonList("beadhouseinfo"));
        try {
            consumeMessage();
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private void processRecord(String key, String value) {
        String[] splitKey = key.split("\\|\\|");
        StringBuilder info = new StringBuilder();
        for (String k : splitKey) {
            info.append(k).append(" ");
        }
        if (splitKey[0].equals("db") && splitKey.length >= 2) {
            processRecordToDb(splitKey[1], value);
        }
    }

    private void processRecordToDb(String tableName, String message) {
        if (tableName.equals("beadhouseinfo")) {
            Gson gson = new Gson();
            //使用google json框架将kafka中的消息反序列化
            BeadhouseInfo info = gson.fromJson(message, BeadhouseInfo.class);
            try {
                //spring data框架将养老院基本信息持久化在MySQL中
                beadhouseInfoRepository.save(info);
                Thread.sleep(3000);
                //若养老院数据成功持久化在MySQL中,则更新elasticsearch中的内容
                processRecordToEs(tableName, message, info);
            } catch (InterruptedException e) {
                LogType.EXCETPION.getLOGGER().error(e);
                e.printStackTrace();
            }
        }
    }

    private void processRecordToEs(String index, String type, BeadhouseInfo info) {
        ElasticsearchBase es;
        try {
            es = ElasticSearchBuider.buildElasticsearchInstance(index, type);
            KAFKA_LOGGER.info(es == null);
            es.insertToEs(info.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            EXCEPTION_LOGGER.error(e.getMessage());
        }
    }

    public ConsumerRecords<String, String> getRecords() {
        return records;
    }

    private void consumeMessage() {
        while (true) {
            records = consumer.poll(2000);
            for (ConsumerRecord record : records) {
                String key = (String) record.key();
                String value = (String) record.value();
                KAFKA_LOGGER.info(value);
                processRecord(key, value);
            }
        }
    }
}
