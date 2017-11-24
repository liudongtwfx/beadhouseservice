package main.java.com.beadhouse.dynamic.html;

import lombok.Data;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;

import java.util.Map;

@Data
public class NodeInfo {
    private int elementId;
    private String classes;
    private int parentId;
    private Map<String, String> attr;
    private int depth;
    private String tagName;
    private String preId;
    private String text;
    private AreaType areaType;
}
