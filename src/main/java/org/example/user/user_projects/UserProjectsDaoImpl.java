package org.example.user.user_projects;

import org.example.Connector;
import org.example.user.UserMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Repository
public class UserProjectsDaoImpl implements UserProjectDao {

    @Override
    public Set<Long> getUserProjectsId(Long userId) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "SELECT * FROM user_projects WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            return UserMapper.toProjectsIds(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addProjectToUser(Long userId, Long projectId) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "INSERT INTO user_projects (user_id, project_id) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userId);
            ps.setLong(2, projectId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserFromProject(Long userId, Long projectId) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "DELETE FROM user_projects WHERE user_id = ? AND project_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userId);
            ps.setLong(2, projectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}