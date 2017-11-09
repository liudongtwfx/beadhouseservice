package main.java.com.beadhouse.dynamic.database.databasemetadata;

import lombok.Data;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Table {
    private List<RDBColumnMetaData> columns;
    private String tableName;
    private String primaryKeyName;

    Table() {
        columns = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("table name is: ").append(tableName != null ? tableName : "null").append("\n");
        sb.append("column size: ").append(columns.size()).append("\n");
        for (RDBColumnMetaData column : columns) {
            sb.append("name: ").append(column.getFullName())
                    .append(", type:").append(column.getJdbcType())
                    .append(", length:").append(column.getLength())
                    .append(", preciesion:").append(column.getPrecision())
                    .append(", comment:").append(column.getComment())
                    .append(", not null:").append(column.isNotNull()).append("\n");
        }
        return sb.toString();
    }
}
