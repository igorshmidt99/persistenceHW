package org.example.position;

import org.example.position.dto.PositionDto;
import org.example.position.model.Position;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionMapper {

    public static Position toEntity(ResultSet resultSet) throws SQLException {
        Position position = new Position();

        while (resultSet.next()) {
            position.setId(resultSet.getLong(1));
            position.setGrade(resultSet.getString(2));
        }
        return position;
    }

    public static Position toEntity(PositionDto dto) {
        return Position.builder()
                .id(dto.getId())
                .grade(dto.getGrade())
                .build();
    }

    public static PositionDto toDto(Position position) {
        return PositionDto.builder()
                .id(position.getId())
                .grade(position.getGrade())
                .build();
    }

    public static long extractId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return resultSet.getLong(1);
    }

}