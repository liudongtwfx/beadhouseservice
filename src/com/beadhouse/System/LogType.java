package com.beadhouse.System;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum LogType {
    KAFKA("kafka"), ES("es"), BEADHOUSEBROWSE("beadhousebrowse"), ARTICLEBROWSE("articlebrowse"),
    EXCETPION("exception"), DEBUGINFO("debuginfo");
    private Logger LOGGER;

    LogType(String type) {
        LOGGER = LogManager.getLogger(type);
    }

    public Logger getLOGGER() {
        return LOGGER;
    }
}
