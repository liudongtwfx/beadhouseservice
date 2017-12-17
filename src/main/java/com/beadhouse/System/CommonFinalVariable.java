package main.java.com.beadhouse.System;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

public final class CommonFinalVariable {
    private static final List<String> jdbcTypes = new ArrayList<>();

    static {
        for (JDBCType type : JDBCType.values()) {
            jdbcTypes.add(type.getName());
        }
    }

    public static final String FIELDS_INTERVAL = "$@$";

    public static List<String> getJdbcTypes() {
        return jdbcTypes;
    }

    public static final String ABSOLUTE_FILE_PATH = "/home/liudong/ideaProject/beadhouseservice/web/WEB-INF/templates/test/";
    public static final String TOMCAT_FILE_PATH = "/home/liudong/program_files/apache-tomcat-9.0.0.M21/webapps/ROOT/WEB-INF/templates/test/";
}
