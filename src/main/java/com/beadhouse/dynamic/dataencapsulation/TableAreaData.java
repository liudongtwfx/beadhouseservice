package main.java.com.beadhouse.dynamic.dataencapsulation;

import java.util.HashSet;
import java.util.Set;

public class TableAreaData extends StructAreaData {

    public TableAreaData(String elementId) {
        super(elementId);
    }

    public TableAreaData() {
    }

    @Override
    public void fillInDatas() {

    }

    @Override
    public Set<String> needTypes() {
        Set<String> needTypes = new HashSet<>();
        needTypes.add("schema");
        needTypes.add("table");
        return needTypes;
    }
}
