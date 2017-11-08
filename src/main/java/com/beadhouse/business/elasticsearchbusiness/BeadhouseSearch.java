package main.java.com.beadhouse.business.elasticsearchbusiness;

import lombok.Data;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class BeadhouseSearch extends ElasticsearchBase {
    @Data
    class BeadhouseBriefInfo implements Serializable {
        private int id;
        private String fullname;
        private String description;
    }

    private static final Set<String> responceKey = new HashSet<>();

    static {
        responceKey.add("id");
        responceKey.add("description");
        responceKey.add("fullname");
        responceKey.add("score");
    }

    public BeadhouseSearch(String host, int port) {
        super(host, port);
    }

    public BeadhouseSearch(Settings settings, String host, int port) {
        super(settings, host, port);
    }

    public List<Map<String, String>> findTopNNearstBeadhouse(GeoPoint point, int N) {
        QueryBuilder builder = QueryBuilders.geoDistanceRangeQuery("geo_location", point);
        GeoDistanceSortBuilder builder1 = new GeoDistanceSortBuilder("geo_location", point);
        List<Map<String, String>> res = new ArrayList<>();
        try {
            SearchResponse response = client.prepareSearch("demo").
                    addSort(builder1).execute().get();
            SearchHit[] hits = response.getHits().getHits();
            for (int i = 0; i < Math.min(N, hits.length); i++) {
                SearchHit hit = hits[i];
                Map<String, Object> ans = hit.getSource();
                Map<String, String> addValue = new HashMap<>();
                for (Map.Entry<String, Object> entry : ans.entrySet()) {
                    BeadhouseBriefInfo info = new BeadhouseBriefInfo();
                    String key = entry.getKey();
                    if (responceKey.contains(key)) {
                        String value = entry.getValue() == null ? "" : entry.getValue().toString();
                        addValue.put(key, value);
                    }
                }
                res.add(addValue);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        return res;
    }
}

