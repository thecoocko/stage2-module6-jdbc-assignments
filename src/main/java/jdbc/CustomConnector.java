package jdbc;

import lombok.Value;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CustomConnector {
    Logger logger = Logger.getLogger(Class.class.getName());
    //postgres.driver=org.postgresql.Driver
    //postgres.url="jdbc:postgresql://localhost:5432/postgres"
    //postgres.password=admin
    //postgres.name=postgres
    public Connection getConnection(String url) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            logger.severe("Something went wrong: "+
                    e.getMessage());
        }
        return connection;
    }

    public Connection getConnection(String url, String user, String password)  {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
