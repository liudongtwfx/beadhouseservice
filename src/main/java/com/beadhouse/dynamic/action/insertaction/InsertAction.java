package main.java.com.beadhouse.dynamic.action.insertaction;

import main.java.com.beadhouse.dynamic.action.Action;

public abstract class InsertAction implements Action {
    protected DataSourceType dataSourceType;

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
