package com.liudong.controller.beadhouse;


import com.liudong.business.kafkabusiness.kafkaConsumer.BeadhouseConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "beadhouse/comment")
public class CommentController {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Map<String, Object> getCommentData(HttpServletRequest request) {
        String beadhouseId = String.valueOf(request.getSession().getAttribute("beadhouseId"));
        if (beadhouseId == null) {
            return null;
        }
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> content = new HashMap<>();
        ConsumerRecords<String, String> records = new BeadhouseConsumer().getRecords();
        for (ConsumerRecord<String, String> record : records) {
            if (beadhouseId.equals(record.key())) {
                content.put(record.key(), record.value());
            }
        }
        res.put("num", content.size());
        res.put("content", content);
        return res;
    }
}
