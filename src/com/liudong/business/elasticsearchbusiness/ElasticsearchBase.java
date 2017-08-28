package com.liudong.business.elasticsearchbusiness;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public abstract class ElasticsearchBase {
    private final static Logger ES_LOGGER = LogManager.getLogger("es");
    private Settings settings;
    protected TransportClient client;
    protected String index;
    protected String type;

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

    ElasticsearchBase(String index, String type) {
        this("127.0.0.1", 9300);
        this.index = index;
        this.type = type;
    }

    public IndexResponse insertToEs(String content) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        ES_LOGGER.info(content);
        for (String field : content.split("\\|\\|")) {
            String[] fv = field.split(":", 2);
            builder.field(fv[0], fv[1]);
        }
        builder.endObject();
        try {
            IndexResponse response = client.prepareIndex(index, type).
                    setSource(builder).execute().get();
            System.out.println(response.status());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateContentByDocid(String docID, Map<String, String> updateValue) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        for (Map.Entry<String, String> entry : updateValue.entrySet()) {
            builder.field(entry.getKey(), entry.getValue());
        }
        builder.endObject();
        try {
            UpdateResponse response = client.prepareUpdate(index, type, docID).
                    setDoc(builder).get();
            System.out.println(response.status());
            return response.status().getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteDocById(String docid) {
        try {
            DeleteResponse response = client.prepareDelete(index, type, docid).execute().get();
            return response.status().getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
