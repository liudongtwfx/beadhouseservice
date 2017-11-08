package main.java.com.beadhouse.System;

public enum LeisureGroupStatus {
    NOTSTARTED("NOTSTARTED"), STARTED("STARTED"), RUNNING("RUNNING"), ENDED("ENDED");
    private String status;

    LeisureGroupStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
