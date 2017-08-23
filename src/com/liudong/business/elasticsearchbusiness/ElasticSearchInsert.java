package com.liudong.business.elasticsearchbusiness;

import org.elasticsearch.client.transport.TransportClient;

public class ElasticSearchInsert implements Runnable {
    private String Index;
    private static final ElasticsearchBase elasticsearchBase = new ElasticsearchBase();

    @Override
    public void run() {
        TransportClient client = elasticsearchBase.getClient();
    }
}
