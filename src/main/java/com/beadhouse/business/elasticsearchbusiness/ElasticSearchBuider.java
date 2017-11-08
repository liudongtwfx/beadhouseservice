package main.java.com.beadhouse.business.elasticsearchbusiness;

public class ElasticSearchBuider {
    public static ElasticsearchBase buildElasticsearchInstance(String index, String topic) {
        if (index.equals("demo") && topic.equals("comment")) {
            return new BeadhouseCommentEs();
        }
        return null;
    }
}
