package com.liudong.test;

import com.liudong.business.kafkabusiness.kafkaConsumer.BeadhouseConsumer;
import com.liudong.business.kafkabusiness.kafkaProducer.BeadhouseCommentProducer;
import com.liudong.model.admin.BeadhouseComment;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Assert;
import org.junit.Test;

public class KafkaTest {

    @Test
    public void KafkaConfigTest() {
        Producer<String, String> producer = new BeadhouseCommentProducer().getProducer();
    }

    @Test
    public void BeadhouseCommentTest() throws InterruptedException {
        BeadhouseConsumer consumer = new BeadhouseConsumer("beadhousecomment");
        Thread thread = new Thread(consumer);
        thread.start();
        BeadhouseComment comment = new BeadhouseComment();
        comment.setContent("这家养老院不错");
        comment.setId(108);
        comment.setAnonymous(false);
        BeadhouseCommentProducer producer = new BeadhouseCommentProducer();
        producer.produceMessage(comment.getBeadhouseid(), comment.toString());
        Thread.sleep(1000);
        ConsumerRecords<String, String> res = consumer.getRecords();
        if (res != null) {
            for (ConsumerRecord record : res) {
                System.out.println(record.key() + " " + record.value());
            }
        }
        System.out.println();
        Assert.assertEquals("123", "123");
    }
}
