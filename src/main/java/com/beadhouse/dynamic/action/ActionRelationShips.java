package main.java.com.beadhouse.dynamic.action;

import lombok.Data;

@Data
public class ActionRelationShips {
    private Action preAction;
    private Action nextAction;
    private ConnectionType connectionType;
}
