package main.java.com.beadhouse.dynamic.datawrapper;


import lombok.Data;

@Data
public class ColumnInfo {
    private String table;
    private String scheme;
    private String columnName;

    public ColumnInfo(String scheme, String table, String columnName) {
        this.table = table;
        this.scheme = scheme;
        this.columnName = columnName;
    }
}
