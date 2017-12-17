package main.java.com.beadhouse.business.neo4jbusiness;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class Neo4jConnector implements AutoCloseable {

    private Driver driver;
    private boolean hasClosed = false;

    Neo4jConnector() {
        connect();
    }

    @Override
    public void close() {
        this.driver.close();
        hasClosed = true;
    }

    public void connect() {
        if (hasClosed || driver == null) {
            driver = GraphDatabase.driver("bolt://localhost:7687",
                    AuthTokens.basic("neo4j", "6820138"));
            hasClosed = false;
        }
    }

    public Driver getDriver() throws IllegalAccessException {
        if (hasClosed) {
            throw new IllegalAccessException("Neo4j driver has been closed");
        }
        return driver;
    }
}
