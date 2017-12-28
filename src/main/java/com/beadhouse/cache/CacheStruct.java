package main.java.com.beadhouse.cache;

import lombok.Data;

@Data
public class CacheStruct {
    private String key;
    private String value;
    private long lastVisitedTimeStamp;
    private float score;
}
