package main.java.com.beadhouse.test;

import main.java.com.beadhouse.business.neo4jbusiness.AddActionToNeo4j;
import main.java.com.beadhouse.dynamic.action.insertaction.DatabaseInsertAction;
import main.java.com.beadhouse.dynamic.action.queryaction.DatabaseQueryAction;
import main.java.com.beadhouse.dynamic.action.returnaction.ReturnAction;
import main.java.com.beadhouse.dynamic.action.updateaction.Conditions;
import main.java.com.beadhouse.dynamic.action.updateaction.DatabaseUpdateAction;
import main.java.com.beadhouse.dynamic.action.updateaction.UpdateAction;
import main.java.com.beadhouse.dynamic.action.updateaction.UpdateType;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateFixedData;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateSingleLineData;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateType;
import main.java.com.beadhouse.dynamic.datawrapper.ColumnInfo;
import main.java.com.beadhouse.dynamic.datawrapper.ExceptionOrFailedData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
                        "nursinghome", "leisuregroup", "people_num"), "INTEGER", "40"));
        validate.setStart();
        ReturnAction failed1 = new ReturnAction(new ExceptionOrFailedData("人数已达到上限"));
        validate.putAction("NO", failed1);
        failed1.save();
        validate.save();
        AddActionToNeo4j.addTwoActionsRelationShip(validate, failed1, "验证失败");
        ValidateAction validatemoney = new ValidateAction(ValidateType.NOTLESSTHAN,
                new ValidateSingleLineData(
                        new ColumnInfo("nursinghome", "vipuserinfo", "account_num"),
                        new ColumnInfo("nursinghome", "leisuregroup", "pay_num")));
        ReturnAction failed2 = new ReturnAction(new ExceptionOrFailedData("人数已达到上限"));
        validatemoney.save();
        failed2.save();
        AddActionToNeo4j.addTwoActionsRelationShip(validate, validatemoney, "验证成功");
        AddActionToNeo4j.addTwoActionsRelationShip(validatemoney, failed2, "验证失败");
        DatabaseInsertAction insertAction = new DatabaseInsertAction("nursinghome", "leisuregrouppeople");
        insertAction.save();
        AddActionToNeo4j.addTwoActionsRelationShip(validate, validatemoney, "下一步");
        UpdateAction minusmoney = new DatabaseUpdateAction("schema",
                "vipuserinfo", "account_num", new Conditions(UpdateType.MINUS));
        UpdateAction add = new DatabaseUpdateAction("schema",
                "leisuregroup", "people", new Conditions(UpdateType.INCREMENT));
        ReturnAction returnSuccess = new ReturnAction(new ExceptionOrFailedData("人数已达到上限"));
        returnSuccess.setEnd();
        returnSuccess.save();
        minusmoney.save();
        add.save();
        AddActionToNeo4j.addTwoActionsRelationShip(validatemoney, insertAction, "下一步");
        AddActionToNeo4j.addTwoActionsRelationShip(validatemoney, insertAction, "验证成功");
        AddActionToNeo4j.addTwoActionsRelationShip(insertAction, minusmoney, "下一步");
        AddActionToNeo4j.addTwoActionsRelationShip(minusmoney, add, "下一步");
        AddActionToNeo4j.addTwoActionsRelationShip(add, returnSuccess, "返回结果");
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("id", "studentId");
        DatabaseQueryAction queryAction = new DatabaseQueryAction("test", "student");
        queryAction.setSelectFields(list);
        queryAction.save();
    }
}
