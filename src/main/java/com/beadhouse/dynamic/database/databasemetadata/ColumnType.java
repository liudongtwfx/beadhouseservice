package main.java.com.beadhouse.dynamic.database.databasemetadata;

public enum ColumnType {
    VARCHAR("varchar"),
    INTEGER("int"),
    LONG("long");
    private String s;

    ColumnType(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}