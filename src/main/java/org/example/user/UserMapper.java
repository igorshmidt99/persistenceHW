package org.example.user;

import org.example.position.dto.PositionDto;
import org.example.user.dto.UserDto;
import org.example.user.dto.UserDtoWithProjects;
import org.example.user.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public final class UserMapper {

    public static User toUser(ResultSet userSet) throws SQLException {
        User user = User.builder().build();
        while (userSet.next()) {
            user.setId(userSet.getLong(1));
            user.setName(userSet.getString(2));
            user.setPosition(userSet.getLong(3));
        }
        return user;
    }

    public static UserDto toDto(User user, PositionDto dto) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .position(dto)
                .build();
    }

    public static UserDtoWithProjects toDtoWithProjects(User user, PositionDto positionDto, Set<String> projects) {
        return UserDtoWithProjects.builder()
                .projects(projects)
                .id(user.getId())
                .name(user.getName())
                .position(positionDto)
                .build();
    }

    public static Set<Long> toProjectsIds(ResultSet resultSet) throws SQLException {
        Set<Long> ids = new HashSet<>();
        while (resultSet.next()) {
            ids.add(resultSet.getLong(2));
        }
        return ids;
    }
}
