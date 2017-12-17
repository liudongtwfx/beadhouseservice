package main.java.com.beadhouse.test;

import main.java.com.beadhouse.business.neo4jbusiness.AddActionToNeo4j;
import main.java.com.beadhouse.dynamic.action.returnaction.ReturnAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateType;
import main.java.com.beadhouse.dynamic.datawrapper.SuccessData;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public class ActionTest {
    @Test
    public void ValidateTest() {
        ValidateAction validate = new ValidateAction(ValidateType.EQUAL);
        validate.setStart();
        validate.setComparingType("int");
        validate.setComparingValue("100");
        validate.save();
    }

    @Test
    public void returnTest() {
        ValidateAction validate = new ValidateAction(ValidateType.EQUAL);
        validate.setStart();
        validate.setComparingType("int");
        validate.setComparingValue("100");
        ReturnAction returnAction = new ReturnAction(new SuccessData("验证成功"));
        validate.putAction("YES", returnAction);
        returnAction.save();
        validate.save();
        new AddActionToNeo4j().addTwoActionsRelationShip(validate, returnAction, "YES");
    }

    @Test
    public void streamTest() throws IOException {
        InetAddress address = InetAddress.getByName("39.106.165.69");
        System.out.println(address.getHostAddress());
        System.out.println(String.valueOf(address.getAddress()));
        System.out.println(address.isReachable(1000));
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.stream().findFirst();
    }
}
