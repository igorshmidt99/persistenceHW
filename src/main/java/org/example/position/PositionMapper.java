package org.example.position;

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

    public static PositionDto toDto(Position position) {
        return PositionDto.builder()
                .id(position.getId())
                .grade(position.getGrade())
                .build();
    }

}