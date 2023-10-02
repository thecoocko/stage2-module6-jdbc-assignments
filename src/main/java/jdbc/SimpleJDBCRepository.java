package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "INSERT INTO myusers(firstname,lastname,age) values(?,?,?)";
    private static final String updateUserSQL = "UPDATE myusers WHERE SET firstname=?, lastname=?,age=? ";
    private static final String deleteUser = "DELETE myusers where id=?";
    private static final String findUserByIdSQL = "SELECT * FROM myusers WHERE id=? ";
    private static final String findUserByNameSQL = "SELECT * FROM myusers WHERE firstname=?";
    private static final String findAllUserSQL = "SELECT * FROM myusers";

    Logger logger = Logger.getLogger(Class.class.getName());
    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private final Properties appProp = new Properties();

    private final String appConfigPath = rootPath + "app.properties";
    private final String driver = appProp.getProperty("postgres.driver");
    private final String url = appProp.getProperty("postgres.url");
    private final String password = appProp.getProperty("postgres.password");
    private final String name = appProp.getProperty("postgres.name");


    public Long createUser(String firstname, String lastname,int age) {
        try{
            connection = CustomDataSource.getInstance(driver,url,name,password).getConnection();

            ps = connection.prepareStatement(createUserSQL,PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1,"Nikita");
            ps.setString(2,"Litvinkov");
            ps.setInt(3, 21);

            ps.execute();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }



    public User findUserById(Long userId) {
        try {
            connection = CustomDataSource.getInstance(driver,url,password,name).getConnection();
            ps = connection.prepareStatement(findUserByIdSQL);

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User resultUser = new User();
                resultUser.setAge(rs.getInt("age"));
                resultUser.setId(rs.getLong("id"));
                resultUser.setFirstName(rs.getString("firstname"));
                resultUser.setLastName(rs.getString("lastname"));

                return resultUser;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public User findUserByName(String userName) {
        return null;
    }

    public List<User> findAllUser() {
        return null;
    }

    public User updateUser() {
        return null;

    }

    private void deleteUser(Long userId) {

    }
}
