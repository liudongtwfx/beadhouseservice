package main.java.com.beadhouse.dynamic.action.validateaction;

public enum ValidateType {
    EQUAL("等于"),
    NOT_EQUAL("不等于"),
    GREATERTHAN("大于"),
    NOTGREATERTHAN("不大于"),
    LESSTHAN("小于"),
    NOTLESSTHAN("不小于");
    private String chineseType;

    ValidateType(String chineseType) {
        this.chineseType = chineseType;
    }

    public String getChineseType() {
        return chineseType;
    }
}
