package org.example.user;

import org.example.position.PositionMapper;
import org.example.position.dto.PositionDto;
import org.example.position.model.Position;
import org.example.project.ProjectMapper;
import org.example.project.dto.ProjectDto;
import org.example.user.dto.UserDto;
import org.example.user.dto.UserDtoWithProjects;
import org.example.user.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class UserMapper {

    public static User toUser(ResultSet userSet) throws SQLException {
        User user = User.builder().build();
        while (userSet.next()) {
            user.setId(userSet.getLong(1));
            user.setName(userSet.getString(2));
//            user.setPosition(userSet.getLong(3));
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

    public static UserDto toDto(User user) {
        Position position = user.getPosition();
        PositionDto positionDto = null;
        if (position != null) positionDto = PositionMapper.toDto(position);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .position(positionDto)
                .build();
    }

    public static UserDtoWithProjects toDtoWithProjects(User user) {
        Set<ProjectDto> projectDtos = user.getProjects().stream()
                .map(ProjectMapper::toDto)
                .collect(Collectors.toSet());
        return UserDtoWithProjects.builder()
                .projects(projectDtos)
                .id(user.getId())
                .name(user.getName())
                .position(PositionMapper.toDto(user.getPosition()))
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
