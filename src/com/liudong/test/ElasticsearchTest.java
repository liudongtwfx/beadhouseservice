package com.liudong.test;

import com.liudong.business.elasticsearchbusiness.BeadhouseSearch;
import org.elasticsearch.common.geo.GeoPoint;

import java.util.List;
import java.util.Map;

public class ElasticsearchTest {
    public static void main(String[] args) {
        BeadhouseSearch search = new BeadhouseSearch("127.0.0.1", 9300);
        GeoPoint point = new GeoPoint();
        point.resetLat(39.0);
        point.resetLon(116.0);
        List<Map<String, String>> res = search.findTopNNearstBeadhouse(point, 2);
        for (Map<String, String> map : res) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
