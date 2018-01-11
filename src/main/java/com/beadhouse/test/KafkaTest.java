package main.java.com.beadhouse.test;

import main.java.com.beadhouse.business.databaseBusiness.SaveToDataBase;
import main.java.com.beadhouse.business.kafkabusiness.kafkaConsumer.BeadhouseInfoConsumer;
import main.java.com.beadhouse.business.kafkabusiness.kafkaProducer.BeadhouseCommentProducer;
import main.java.com.beadhouse.model.admin.BeadhouseComment;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Assert;
import org.junit.Test;

public class KafkaTest {

    @Test
    public void BeadhouseCommentTest()
            throws InterruptedException {
        BeadhouseInfoConsumer consumer = new BeadhouseInfoConsumer();
        Thread thread = new Thread(consumer);
        thread.start();
        Thread.sleep(2000);
        BeadhouseComment comment = new BeadhouseComment();
        comment.setContent("这家养老院不错");
        comment.setId(108);
        comment.setAnonymous(false);
        BeadhouseCommentProducer producer = new BeadhouseCommentProducer();
        for (int i = 0; i < 100; i++) {
            producer.produceMessage(String.valueOf(comment.getBeadhouseid()), comment.toString());
            Thread.sleep(1000);
        }
        BeadhouseComment comment1 = null;
        Thread.sleep(10000);
        ConsumerRecords<String, String> res = consumer.getRecords();
        if (res != null) {
            System.out.println(res.isEmpty());
            for (ConsumerRecord record : res) {
                System.out.println(record);
                comment1 = DeserialBeadhouseComment((String) record.value());
            }
        }
        assert res != null;
        Assert.assertEquals(comment.toString(), comment1.toString());
    }

    private BeadhouseComment DeserialBeadhouseComment(String content) {
        System.out.println(content);
        return SaveToDataBase.getBeadhouesCommentByValue(content);
    }
}
