package org.example.user.repository;

import org.example.position.model.Position;
import org.example.project.model.Project;
import org.example.user.model.User;

import java.util.Set;

public interface UserRepository {
    User getUserById(Long id);
    User addUser(String name, Position position);
    void deleteUser(Long id);
    User updateUser(User user);
}
