package com.liudong.business.elasticsearchbusiness;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;

import java.util.concurrent.ExecutionException;

public class ArticleESBusiness extends ElasticsearchBase {
    private static final String INDEX = "demo";
    private static final String TYPE = "article";

    public ArticleESBusiness() {
        super(INDEX, TYPE);
    }

    public SearchHits getArticleByDocId(String docId) {
        try {
            SearchResponse response = client.prepareSearch(INDEX, TYPE, docId).execute().get();
            return response.getHits();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
