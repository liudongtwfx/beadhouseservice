package com.liudong.business.elasticsearchbusiness;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

class ElasticsearchBase {
    private Settings settings;
    TransportClient client;

    ElasticsearchBase(String host, int port) {
        this.client = new PreBuiltTransportClient(Settings.EMPTY);
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    ElasticsearchBase(Settings settings, String host, int port) {
        this.settings = settings;
        this.client = new PreBuiltTransportClient(settings);
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    ElasticsearchBase() {
        this("127.0.0.1", 9300);
    }

    public TransportClient getClient() {
        return client;
    }
}
