package com.liudong.business.elasticsearchbusiness;

import org.elasticsearch.common.settings.Settings;

public class ElasticSearchInsert extends ElasticsearchBase implements Runnable {
    private String Index;

    ElasticSearchInsert(String host, int port) {
        super(host, port);
    }

    ElasticSearchInsert(Settings settings, String host, int port) {
        super(settings, host, port);
    }

    ElasticSearchInsert(String index, String type) {
        super(index, type);
    }

    @Override
    public void run() {
    }
}
