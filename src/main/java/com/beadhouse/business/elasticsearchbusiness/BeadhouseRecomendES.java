package main.java.com.beadhouse.business.elasticsearchbusiness;

import main.java.com.beadhouse.System.LogType;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

public class BeadhouseRecomendES extends ElasticsearchBase {
    private static final String index = "demo";
    private static final String type = "beadhouseRecommed";

    public BeadhouseRecomendES() {
        super();
    }

    public SearchHits getBeadhouseByVIPUser(String vipuserId) {
        try {
            SearchResponse response = client.prepareSearch().setIndices(index).setTypes(type)
                    .setQuery(QueryBuilders.termQuery("vipuserId", vipuserId)).execute().get();
            return response.getHits();
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e.getMessage());
            return null;
        }
    }
}
