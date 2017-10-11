package com.beadhouse.test;

import com.beadhouse.business.databaseBusiness.SaveToDataBase;
import com.beadhouse.business.kafkabusiness.kafkaConsumer.BeadhouseConsumer;
import com.beadhouse.business.kafkabusiness.kafkaProducer.BeadhouseCommentProducer;
import com.beadhouse.model.admin.BeadhouseComment;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class KafkaTest {

    @Test
    public void KafkaConfigTest() {
        Producer<String, String> producer = new BeadhouseCommentProducer().getProducer();
    }

    @Test
    public void BeadhouseCommentTest()
            throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BeadhouseConsumer consumer = new BeadhouseConsumer("beadhousecomment");
        Thread thread = new Thread(consumer);
        thread.start();
        BeadhouseComment comment = new BeadhouseComment();
        comment.setContent("这家养老院不错");
        comment.setId(108);
        comment.setAnonymous(false);
        BeadhouseCommentProducer producer = new BeadhouseCommentProducer();
        producer.produceMessage(String.valueOf(comment.getBeadhouseid()), comment.toString());
        Thread.sleep(500);
        BeadhouseComment comment1 = null;
        ConsumerRecords<String, String> res = consumer.getRecords();
        if (res != null) {
            for (ConsumerRecord record : res) {
                comment1 = DeserialBeadhouseComment((String) record.value());
            }
        }
        assert res != null;
        Assert.assertEquals(comment.toString(), comment1.toString());
    }

    private BeadhouseComment DeserialBeadhouseComment(String content)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        System.out.println(content);
        return SaveToDataBase.getBeadhouesCommentByValue(content);
    }
}
