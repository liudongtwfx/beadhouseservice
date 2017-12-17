package main.java.com.beadhouse.dynamic.datawrapper;

public class ExceptionOrFailedData implements Data {
    private String msg;
    private DataSourceType type;

    public ExceptionOrFailedData(String msg) {
        this.msg = msg;
    }

    @Override
    public void setSource() {
        type = DataSourceType.NONE;
    }

    @Override
    public String toString() {
        return msg;
    }

    @Override
    public String wrapDataToString() {
        return msg;
    }
}
