package org.example.user.dao;

import org.example.Connector;
import org.example.user.UserMapper;
import org.example.user.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserById(Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            return UserMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User addUser(String name, Long position) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "INSERT INTO users (name, position) VALUES (?, ?) RETURNING id, name, position";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, position);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return UserMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(Long position, Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "UPDATE users SET position = ? WHERE id = ? RETURNING id, name, position";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, position);
            ps.setLong(2, id);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return UserMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(String name, Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "UPDATE users SET name = ? WHERE id = ? RETURNING id, name, position";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, id);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return UserMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(String name, Long position, Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "UPDATE users SET name = ?, position = ? WHERE id = ? RETURNING id, name, position";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, position);
            ps.setLong(3, id);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return UserMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}