package main.java.com.beadhouse.dynamic.actionthandler.validateactionhandler;

import main.java.com.beadhouse.dynamic.actionthandler.ActionHandler;

public interface ValidateActionHandler extends ActionHandler {

    boolean equals();

    boolean notEquals();

    boolean lessThan();

    boolean greaterThan();

    default boolean notGreaterThan() {
        return lessThan() || equals();
    }

    default boolean notLessThan() {
        return greaterThan() || equals();
    }
}
