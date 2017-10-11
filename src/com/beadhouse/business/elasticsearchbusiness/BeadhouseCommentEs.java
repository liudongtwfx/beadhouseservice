package com.beadhouse.business.elasticsearchbusiness;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;

public final class BeadhouseCommentEs extends ElasticsearchBase {

    private static final String index = "demo";
    private static final String type = "comment";

    public BeadhouseCommentEs() {
        super(index, type);
    }

    public SearchHits getCommentByBeadhouseId(int beadhouseid) throws IOException {
        try {
            SearchResponse response = client.prepareSearch().setIndices(index).setTypes(type)
                    .setQuery(QueryBuilders.matchQuery("beadhouseid", beadhouseid))
                    .setQuery(QueryBuilders.matchQuery("replyed", false)).execute().get();
            System.out.println(response.status());
            return response.getHits();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
