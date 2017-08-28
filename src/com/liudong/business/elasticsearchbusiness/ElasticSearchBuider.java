package com.liudong.business.elasticsearchbusiness;

public class ElasticSearchBuider {
    public static ElasticsearchBase buildElasticsearchInstance(String index, String topic) {
        if (index.equals("demo") && topic.equals("comment")) {
            return new BeadhouseCommentEs();
        }
        return null;
    }
}
