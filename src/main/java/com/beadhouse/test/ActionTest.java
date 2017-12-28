package main.java.com.beadhouse.test;

import main.java.com.beadhouse.business.neo4jbusiness.AddActionToNeo4j;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.queryaction.DatabaseQueryAction;
import main.java.com.beadhouse.dynamic.action.returnaction.ReturnAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateFixedData;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateType;
import main.java.com.beadhouse.dynamic.datawrapper.ColumnInfo;
import main.java.com.beadhouse.dynamic.datawrapper.SuccessData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

public class ActionTest {
    @Test
    public void ValidateTest() {
        ValidateAction validate = new ValidateAction(ValidateType.EQUAL,
                new ValidateFixedData(new ColumnInfo(
                        "demo", "student", "id"), "INTEGER", "40"));
        validate.setStart();
        validate.save();
    }

    @Test
    public void returnTest() {
        ValidateAction validate = new ValidateAction(ValidateType.EQUAL,
                new ValidateFixedData(new ColumnInfo(
                        "demo", "student", "id"), "INTEGER", "40"));
        validate.setStart();
        ReturnAction returnAction = new ReturnAction(new SuccessData("验证成功"));
        validate.putAction("YES", returnAction);
        returnAction.save();
        validate.save();
        new AddActionToNeo4j().addTwoActionsRelationShip(validate, returnAction, "YES");
    }

    @Test
    public void streamTest() throws IOException {
        String res = RedisClientConnector.getLocalRedis().info("memory");
        System.out.println(res);
//        System.out.println(address.getHostAddress());
//        System.out.println(address.isReachable(10000));
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("id", "studentId");
        DatabaseQueryAction queryAction = new DatabaseQueryAction("test", "student");
        queryAction.setSelectFields(list);
        queryAction.save();
    }
}
