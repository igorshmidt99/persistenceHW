package org.example.user;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto addUser(UserDto userDto);
    void deleteUser(String name);
    void deleteUser(Long id);
    UserDto updateUser(UserDto dto, Long id);
}