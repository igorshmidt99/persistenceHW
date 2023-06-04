package org.example.user;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto addUser(UserRequestDto userDto);
    void deleteUser(Long id);
    UserDto updateUser(UserRequestDto dto, Long id);
    UserDtoWithProjects addProjectToUser(Long userId, Long projectId);
    UserDtoWithProjects getUserWithProjects(Long userId);
    void deleteUserFromProject(Long userId, Long projectId);
}