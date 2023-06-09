package org.example.user.user_projects;

import java.util.Set;

public interface UserProjectDao {
    void addProjectToUser(Long userId, Long projectId);
    Set<Long> getUserProjectsId(Long userId);
    void deleteUserFromProject(Long userId, Long projectId);
}
