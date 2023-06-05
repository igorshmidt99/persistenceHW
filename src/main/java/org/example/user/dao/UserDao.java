package org.example.user.dao;

import org.example.user.model.User;

public interface UserDao {
    User getUserById(Long id);
    User addUser(String name, Long position);
    void deleteUser(Long id);
    User updateUser(Long position, Long id);
    User updateUser(String name, Long id);
    User updateUser(String name, Long position, Long id);
}