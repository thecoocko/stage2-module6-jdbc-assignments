package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "";
    private static final String updateUserSQL = "";
    private static final String deleteUser = "";
    private static final String findUserByIdSQL = "";
    private static final String findUserByNameSQL = "";
    private static final String findAllUserSQL = "";

    public Long createUser() {
        return null;

    }

    public User findUserById(Long userId) {
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
