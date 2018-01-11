package main.java.com.beadhouse.dynamic.action.insertaction;

import main.java.com.beadhouse.dynamic.action.AbstractActionImpl;
import main.java.com.beadhouse.dynamic.action.ActionType;
import main.java.com.beadhouse.dynamic.action.DataSourceType;

public abstract class InsertAction extends AbstractActionImpl {
    protected DataSourceType dataSourceType;

    public InsertAction() {
        setActionType(ActionType.INSERT);
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
