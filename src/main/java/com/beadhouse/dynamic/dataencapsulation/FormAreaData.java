package main.java.com.beadhouse.dynamic.dataencapsulation;

import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;

import java.util.Set;

public class FormAreaData extends StructAreaData {
    public FormAreaData() {
        super();
    }

    @Override
    public void fillInDatas() {

    }

    @Override
    public Set<String> needTypes() {
        return null;
    }

    @Override
    public void setAreaType() {
        areaType = AreaType.FORM;
    }
}
