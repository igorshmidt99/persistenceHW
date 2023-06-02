package org.example.user;

import org.example.GlobalVariables;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserById(Long id) {
        try (Connection connection = DriverManager.getConnection(
                GlobalVariables.getDbUrl(),
                GlobalVariables.getLogin(),
                GlobalVariables.getPASSWORD())) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User addUser(String name, Long position) {
        try (Connection connection = DriverManager.getConnection(
                GlobalVariables.getDbUrl(),
                GlobalVariables.getLogin(),
                GlobalVariables.getPASSWORD()
        )) {
            String sql = "INSERT INTO users (name, position) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, position);
            ResultSet resultSet = ps.executeQuery();
            return mapUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String name) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User updateUser(Long position, Long id) {
        return null;
    }

    @Override
    public User updateUser(String name, Long id) {
        return null;
    }

    @Override
    public User updateUser(String name, Long position, Long id) {
        return null;
    }

    private User mapUser(ResultSet userSet) throws SQLException {
        User user = User.builder().build();
        while (userSet.next()) {
            user.setId(userSet.getLong(1));
            user.setName(userSet.getString(2));
            user.setPosition(userSet.getInt(3));
        }
        return user;
    }

}