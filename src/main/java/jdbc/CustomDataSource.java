package jdbc;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private CustomDataSource(String driver, String url, String password, String name) {
    }

    public static CustomDataSource getInstance() {

        return null;
    }

}
