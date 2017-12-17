package main.java.com.beadhouse.business.neo4jbusiness;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.action.Action;
import org.neo4j.driver.v1.Session;

public class AddActionToNeo4j {
    private Neo4jConnector neo4jConnector;

    public AddActionToNeo4j() {
        neo4jConnector = new Neo4jConnector();
    }

    public boolean addAction(Action action, String json) {
        try {
            Session session = neo4jConnector.getDriver().session();
            session.writeTransaction(tx -> tx.run(getActionCypher(action, json)));
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            neo4jConnector.close();
        }
        return false;
    }

    private String getActionCypher(Action action, String json) {
        StringBuilder actionCypher = new StringBuilder();
        actionCypher.append("CREATE (").append("action:")
                .append(action.getActionType()).append(" ").append(json).append(" )");
        LogType.DEBUGINFO.getLOGGER().debug(actionCypher.toString());
        return actionCypher.toString();
    }

    public boolean addTwoActionsRelationShip(Action left, Action right, String relationShip) {
        try {
            Session session = neo4jConnector.getDriver().session();
            session.writeTransaction(tx -> tx.run(getCreateRelationShipCypher(left, right, relationShip)));
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            neo4jConnector.close();
        }
        return false;
    }

    private String getCreateRelationShipCypher(Action left, Action right, String relationShip) {
        StringBuilder sb = new StringBuilder();
        sb.append("MATCH ").append("(action1:").append(left.getActionType())
                .append("),(action2:").append(right.getActionType()).append(")");
        sb.append(" WHERE action1.actionId=\"").append(left.getUUID()).append("\" and action2.actionId=\"")
                .append(right.getUUID()).append("\"");
        sb.append(" CREATE (action1) -[r:").append(relationShip).append("]->(action2)");
        LogType.DEBUGINFO.getLOGGER().debug(sb.toString());
        return sb.toString();
    }
}
