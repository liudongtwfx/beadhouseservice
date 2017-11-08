package main.java.com.beadhouse.System;

public enum AuthApplyStatus {
    TOBEAPPROVE("TOBEAPPROVE"), NO("NO"), YES("YES");
    private String status;

    AuthApplyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
