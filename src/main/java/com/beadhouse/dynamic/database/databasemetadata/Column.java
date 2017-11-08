package main.java.com.beadhouse.dynamic.database.databasemetadata;

import lombok.Data;

@Data
public class Column {
    private String type;
    private String name;
    private boolean isPrimaryKey;
    private boolean autoIncrement;
    private int length;
}
