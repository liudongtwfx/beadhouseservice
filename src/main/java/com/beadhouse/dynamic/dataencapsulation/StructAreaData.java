package main.java.com.beadhouse.dynamic.dataencapsulation;

import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class StructAreaData {
    protected String url;
    protected String elementId;
    protected AreaType areaType;
    protected String schema;
    protected String table;
    protected List<String> columns;
    protected List<Map<String, String>> datas;

    public StructAreaData(String elementId) {
        this.elementId = elementId;
        setAreaType();
    }

    public StructAreaData() {
        setAreaType();
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public StructAreaData setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public StructAreaData setTable(String table) {
        this.table = table;
        return this;
    }

    public StructAreaData setColumns(List<String> columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("areatype:").append(areaType).append("||").
                append("schema:").append(schema).append("||").
                append("table:").append(table).append("||").
                append("columns:");
        if (columns != null) {
            columns.forEach(column -> sb.append(column).append("#%@"));
            sb.delete(sb.length() - 3, sb.length());
        }
        return sb.toString();
    }

    public abstract void fillInDatas();

    public List<Map<String, String>> getDatas() {
        return datas;
    }

    public abstract Set<String> needTypes();

    public AreaType getAreaType() {
        return areaType;
    }

    public String getElementId() {
        return elementId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public abstract void setAreaType();

    public String getSchema() {
        return schema;
    }

    public String getTable() {
        return table;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setDatas(List<Map<String, String>> datas) {
        this.datas = datas;
    }
}