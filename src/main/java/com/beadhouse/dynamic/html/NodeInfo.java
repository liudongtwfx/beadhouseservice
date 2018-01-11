package main.java.com.beadhouse.dynamic.html;

import lombok.Data;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;

import java.util.Map;

@Data
public class NodeInfo {
    private int elementId;//前序遍历时的id
    private String classes;//类名
    private int parentId;//父结点的id
    private Map<String, String> attr;//属性列表
    private int depth;//结点的深度
    private String tagName;//标签名
    private String preId;//原来的id属性
    private String text;//文本
    private AreaType areaType;//区域类型
}
