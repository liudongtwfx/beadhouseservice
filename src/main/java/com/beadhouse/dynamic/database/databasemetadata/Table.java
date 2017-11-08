package main.java.com.beadhouse.dynamic.database.databasemetadata;

import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<RDBColumnMetaData> columns;

    public String getTableName() {
        return tableName;
    }

    Table() {
        columns = new ArrayList<>();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;

    public List<RDBColumnMetaData> getColumns() {
        return columns;
    }
}
