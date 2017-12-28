package main.java.com.beadhouse.dynamic.dataencapsulation;

import main.java.com.beadhouse.dynamic.actionthandler.queryactionhandler.DatabaseQueryActionHandler;
import main.java.com.beadhouse.dynamic.datawrapper.SQLResultData;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TableAreaData extends StructAreaData {
    private SQLResultData sqlResultData;
    private Map<String, String> paramsMap;

    public TableAreaData(String elementId) {
        super(elementId);
    }

    public TableAreaData() {
        super();
    }

    @Override
    public void fillInDatas() {
        this.sqlResultData = new DatabaseQueryActionHandler(schema, table, paramsMap).handle(null);
    }

    @Override
    public Set<String> needTypes() {
        Set<String> needTypes = new HashSet<>();
        needTypes.add("schema");
        needTypes.add("table");
        return needTypes;
    }

    @Override
    public void setAreaType() {
        this.areaType = AreaType.TABLE;
    }

    public SQLResultData getSqlResultData() {
        return sqlResultData;
    }

    public void setParamsMap(Map<String, String> paramsMap) {
        this.paramsMap = paramsMap;
    }
}
