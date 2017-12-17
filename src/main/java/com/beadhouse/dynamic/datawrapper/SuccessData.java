package main.java.com.beadhouse.dynamic.datawrapper;

public class SuccessData implements Data {
    private DataSourceType type;
    private String msg;

    public SuccessData(String msg) {
        this.msg = msg;
    }

    @Override
    public void setSource() {
        type = DataSourceType.NONE;
    }

    @Override
    public String wrapDataToString() {
        return msg;
    }
}
