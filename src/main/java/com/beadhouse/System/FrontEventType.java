package main.java.com.beadhouse.System;

public enum FrontEventType {
    CLICK("点击"),
    BLUR(""),
    CHANGE(""),
    KEYDOWN(""),
    KEYUP(""),
    SELECT(""),
    FOCUS("得到焦点"),
    MOUSEOVER(""),
    MOUSEOUT("");
    private String chineseName;

    FrontEventType(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getChineseName() {
        return chineseName;
    }
}
