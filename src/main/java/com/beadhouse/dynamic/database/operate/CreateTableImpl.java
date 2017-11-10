package main.java.com.beadhouse.dynamic.database.operate;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.database.databasemetadata.Table;
import org.hswebframework.ezorm.rdb.RDBDatabase;
import org.hswebframework.ezorm.rdb.executor.AbstractJdbcSqlExecutor;
import org.hswebframework.ezorm.rdb.executor.SqlExecutor;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;
import org.hswebframework.ezorm.rdb.meta.RDBDatabaseMetaData;
import org.hswebframework.ezorm.rdb.meta.builder.ColumnBuilder;
import org.hswebframework.ezorm.rdb.meta.builder.TableBuilder;
import org.hswebframework.ezorm.rdb.render.dialect.MysqlRDBDatabaseMetaData;
import org.hswebframework.ezorm.rdb.simple.SimpleDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public final class CreateTableImpl {
    private String schema;

    public CreateTableImpl() {
        schema = "test";
    }

    public CreateTableImpl(String schema) {
        this.schema = schema;
    }

    public String createTableByTable(Table table) {
        String messeage = validate(table);
        if (!"true".equals(messeage)) {
            return messeage;
        }
        DatabaseOperation operation = new DatabaseOperation(schema);
        try {
            TableBuilder builder = operation.getDatabase().createOrAlter(table.getTableName());
            for (RDBColumnMetaData column : table.getColumns()) {
                ColumnBuilder cb = builder.addColumn().name(column.getName());
                cb = buildType(cb, column);
                if (table.getPrimaryKeyName().equals(column.getName())) {
                    cb = cb.primaryKey();
                }
                if (column.isNotNull()) {
                    cb = cb.notNull();
                }
                if (inArray(hasLengthType, column.getJdbcType().getName())) {
                    cb = cb.length(column.getLength());
                }
                builder = cb.comment(column.getComment()).commit();
            }
            builder.commit();
            return "success";
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            return e.toString();
        } finally {
            operation.close();
        }
    }

    private String validate(Table table) {
        Set<String> columnNames = new HashSet<>();
        for (RDBColumnMetaData metaData : table.getColumns()) {
            String name = metaData.getName();
            if (columnNames.contains(name)) {
                return "有重复字段: " + name;
            }
            if (inArray(hasLengthType, metaData.getJdbcType().toString()) && metaData.getLength() <= 0) {
                return name + ": 请输入有效的长度";
            }
            columnNames.add(name);
        }
        if (!columnNames.contains(table.getPrimaryKeyName())) {
            return "请指定主键";
        }
        return "true";
    }

    private ColumnBuilder buildType(ColumnBuilder cb, RDBColumnMetaData column) {
        switch (column.getJdbcType()) {
            case INTEGER:
                return cb.integer();
            case VARCHAR:
                return cb.varchar(column.getLength());
            case CHAR:
                return cb.varchar(column.getLength());
            case TIMESTAMP:
                return cb.datetime();
            case TINYINT:
                return cb.tinyint();
            case CLOB:
                return cb.clob();
            case NUMERIC:
                return cb.number(column.getLength(), column.getPrecision());
            default:
                return cb;
        }
    }

    private static String[] hasLengthType = {"VARCHAR", "CHAR"};
    private static String[] hasPreciseType = {"Double"};

    private boolean inArray(String[] arrays, String s) {
        for (String ss : arrays) {
            if (s.equals(ss)) {
                return true;
            }
        }
        return false;
    }
}