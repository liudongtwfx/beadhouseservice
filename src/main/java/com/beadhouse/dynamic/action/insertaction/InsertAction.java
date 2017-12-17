package main.java.com.beadhouse.dynamic.action.insertaction;

import main.java.com.beadhouse.dynamic.action.ActionImpl;
import main.java.com.beadhouse.dynamic.action.ActionType;
import main.java.com.beadhouse.dynamic.action.DataSourceType;
import main.java.com.beadhouse.dynamic.action.Lock;

public abstract class InsertAction extends ActionImpl {
    protected DataSourceType dataSourceType;

    InsertAction() {
        setActionType(ActionType.INSERT);
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
