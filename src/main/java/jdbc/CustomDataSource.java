package jdbc;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

@Getter
@Setter
public class CustomDataSource implements DataSource {
    Logger logger = Logger.getLogger(Class.class.getName());
    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private final String appConfigPath = rootPath + "app.properties";
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.name = name;
    }

    public static CustomDataSource getInstance() {

        if(instance==null){
            Properties properties = new Properties();
            try {
                properties.load(CustomDataSource.class.getClassLoader().getResourceAsStream("app.properties"));
            }catch (IOException e){
                throw  new RuntimeException(e);
            }
            String driver = appProps.getProperty("postgres.driver");
            String url = appProps.getProperty("postgres.url");
            String password = appProps.getProperty("postgres.password");
            String name = appProps.getProperty("postgres.name");

            return new CustomDataSource(driver, url, password, name);
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
