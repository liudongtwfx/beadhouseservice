package com.liudong.test;

import com.liudong.business.elasticsearchbusiness.BeadhouseCommentEs;
import com.liudong.model.admin.BeadhouseComment;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElasticsearchTest {

    private String docid;

    @Test
    public void maintest() throws IOException {
        commentInsertTest();
        commentUpdateTest();
        getCommentByBeadhouseid();
        deleteCommentByDocid();
    }

    public void commentInsertTest() throws IOException {
        BeadhouseCommentEs es = new BeadhouseCommentEs();
        BeadhouseComment comment = new BeadhouseComment();
        comment.setContent("这家养老院环境很好");
        comment.setBeadhouseid(108);
        comment.setAnonymous(false);
        comment.setReplyed(false);
        IndexResponse responce = es.insertToEs(comment.toString());
        docid = responce.getId();
        System.out.println("docid : " + docid);
        Assert.assertEquals(RestStatus.CREATED.getStatus(), responce.status().getStatus());
    }

    public void commentUpdateTest() throws IOException {
        BeadhouseCommentEs es = new BeadhouseCommentEs();
        Map<String, String> updateMap = new HashMap<>();
        updateMap.put("content", "这家养老院不行啊");
        int responce = es.updateContentByDocid(docid, updateMap);
        Assert.assertEquals(RestStatus.OK.getStatus(), responce);
    }

    public void getCommentByBeadhouseid() throws IOException {
        SearchHits hits = new BeadhouseCommentEs().getCommentByBeadhouseId(108);
        for (SearchHit hit : hits.getHits()) {
            for (Map.Entry<String, Object> entry : hit.getSource().entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
                if (docid == null && entry.getKey().equals("_id")) {
                    docid = (String) entry.getValue();
                }
            }
        }
        Assert.assertNotNull(hits);
        Assert.assertTrue(hits.getHits().length > 0);
    }

    public void deleteCommentByDocid() throws IOException {
        int res = new BeadhouseCommentEs().deleteDocById(docid);
        Assert.assertEquals(RestStatus.OK.getStatus(), res);
    }
}
