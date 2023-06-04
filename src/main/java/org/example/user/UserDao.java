package org.example.user;

import java.util.Set;

public interface UserDao {
    User getUserById(Long id);
    User addUser(String name, Long position);
    void deleteUser(Long id);
    User updateUser(Long position, Long id);
    User updateUser(String name, Long id);
    User updateUser(String name, Long position, Long id);
}