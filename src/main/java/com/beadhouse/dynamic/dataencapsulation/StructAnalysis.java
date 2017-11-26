package main.java.com.beadhouse.dynamic.dataencapsulation;

import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StructAnalysis {
    public static StructAreaData analysis(String elementId, String structdatas) {
        String[] params = structdatas.split("\\|\\|");
        StructAreaData areaData = null;
        for (String param : params) {
            String[] kv = param.split("=");
            if (kv[0].equals("areatype")) {
                areaData = getStruct(AreaType.valueOf(kv[1]));
            } else if (areaData == null) {
                continue;
            }
            if (kv[0].equals("schema")) {
                areaData.setSchema(kv[1]);
            } else if (kv[1].equals("table")) {
                areaData.setTable(kv[1]);
            } else if (kv[1].equals("columns")) {
                areaData.setColumns(getColumns(kv[1]));
            }
        }
        if (areaData != null) {
            areaData.setElementId(elementId);
        }
        return areaData;
    }

    private static List<String> getColumns(String input) {
        return Arrays.asList(input.split("#%@"));
    }

    public static StructAreaData getStruct(AreaType type) {
        if (type == null) {
            return null;
        }
        switch (type) {
            case TABLE:
                return new TableAreaData();
            default:
                return null;
        }
    }

    public List<StructAreaData> getStructAreaDatas(String view) {
        Map<String, String> viewDatas = RedisClientConnector.getRedis().hgetAll(view);
        if (viewDatas == null || viewDatas.size() == 0) {
            return new ArrayList<>();
        }
        List<StructAreaData> structAnalyses = new ArrayList<>();
        for (Map.Entry<String, String> entry : viewDatas.entrySet()) {
            structAnalyses.add(analysis(entry.getKey(), entry.getValue()));
        }
        return structAnalyses;
    }
}
