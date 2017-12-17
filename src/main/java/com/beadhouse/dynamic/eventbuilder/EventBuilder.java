package main.java.com.beadhouse.dynamic.eventbuilder;

import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateType;

public class EventBuilder {
    public static Action build(Action prevAction, ConnecterActivity connecter) {
        return new ValidateAction(ValidateType.EQUAL);
    }
}
