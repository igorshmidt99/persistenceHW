package org.example.position.dao;

import org.example.Connector;
import org.example.position.PositionMapper;
import org.example.position.model.Position;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PositionDaoImpl implements PositionDao {

    @Override
    public Position getById(Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "SELECT * FROM positions WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            return PositionMapper.toEntity(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Position getByGrade(String grade) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "SELECT * FROM positions WHERE grade = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grade);
            ResultSet resultSet = ps.executeQuery();
            return PositionMapper.toEntity(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Position createPosition(String grade) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "INSERT INTO positions (grade) VALUES (?) RETURNING id, grade";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grade);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return PositionMapper.toEntity(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Position update(Long id, String grade) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "UPDATE positions SET grade = ? WHERE id = ? RETURNING id, grade";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grade);
            ps.setLong(2, id);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return PositionMapper.toEntity(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getPositionId(String grade) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "SELECT id FROM positions WHERE grade = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grade);
            ResultSet resultSet = ps.executeQuery();
            return PositionMapper.extractId(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByGrade(String grade) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "DELETE FROM positions WHERE grade = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grade);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = Connector.getConnection()) {
            String sql = "DELETE FROM positions WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}