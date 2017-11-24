package main.java.com.beadhouse.dynamic.action.deleteaction;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.action.Callback;
import main.java.com.beadhouse.dynamic.database.operate.DatabaseOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleteAction extends DeleteAction {
    private String schema;
    private String tablenName;
    private int id;

    public DatabaseDeleteAction(String schema, String tableName, int id) {
        this.schema = schema;
        this.tablenName = tableName;
        this.id = id;
    }

    @Override
    public String process(Callback callback) {
        return null;
    }

    private boolean deleteFromTableId() {
        String deleteSQL = "delete from " + tablenName + " where id=" + String.valueOf(id);
        DatabaseOperation operation = new DatabaseOperation(schema);
        try {
            PreparedStatement statement = operation.getConnection().prepareStatement(deleteSQL);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LogType.EXCETPION.getLOGGER().error(e);
            return false;
        } finally {
            operation.close();
        }
    }
}
