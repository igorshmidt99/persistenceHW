package org.example.project.dao;

import org.example.Connector;
import org.example.project.ProjectMapper;
import org.example.project.model.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Set;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Override
    public Project addProject(String name) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "INSERT INTO projects (name) VALUES (?) RETURNING id, name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return ProjectMapper.toProject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project getProjectById(Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "SELECT * FROM projects WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            return ProjectMapper.toProject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project update(String name, Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "UPDATE projects SET name = ? WHERE id = ? RETURNING id, name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, id);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return ProjectMapper.toProject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "DELETE FROM projects WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> getUserProjectsById(Set<Long> projectIds) {
        try (Connection connection = Connector.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT name FROM projects WHERE (id) IN ");
            projectIds.forEach(id -> sql.append(String.format("(%d), ", id)));
            sql.setLength(sql.length() - 2);
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery(sql.toString());
            return ProjectMapper.toProjectsStrings(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}