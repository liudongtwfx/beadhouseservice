package main.java.com.beadhouse.dynamic.action;

public enum ActionType {
    INSERT("插入"),
    QUERY("查询"),
    DELETE("删除"),
    UPDATE("更新"),
    VALIDATE("验证"),
    PUBLISH("发布"),
    SUBSCRIBE("订阅"),
    RETURN("返回");
    private String chineseName;

    ActionType(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getChineseName() {
        return chineseName;
    }
}