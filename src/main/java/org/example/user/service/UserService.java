package org.example.user.service;

import org.example.user.dto.UserDto;
import org.example.user.dto.UserDtoWithProjects;
import org.example.user.dto.UserRequestDto;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto addUser(UserRequestDto userDto);
    void deleteUser(Long id);
    UserDto updateUser(UserRequestDto dto, Long id);
    UserDtoWithProjects addProjectToUser(Long userId, Long projectId);
    UserDtoWithProjects getUserWithProjects(Long userId);
    void deleteUserFromProject(Long userId, Long projectId);
}