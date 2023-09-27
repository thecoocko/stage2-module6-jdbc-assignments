package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class CustomConnector {
    Logger logger = Logger.getLogger(Class.class.getName());
    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private final String appConfigPath = rootPath + "app.properties";

    public Connection getConnection(String url) {
        Connection connection = null;
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String driver = appProps.getProperty("postgres.driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            logger.severe("Something went wrong: "+
                    e.getMessage());
        }
        return connection;
    }

    public Connection getConnection(String url, String user, String password)  {
        Connection connection = null;
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            String driver = appProps.getProperty("postgres.driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
