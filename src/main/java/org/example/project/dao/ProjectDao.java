package org.example.project.dao;

import org.example.project.model.Project;

import java.util.Set;

public interface ProjectDao {
    Project addProject(String name);
    Project getProjectById(Long id);
    Project update(String name, Long id);
    void delete(Long id);
    Set<String> getUserProjectsById(Set<Long> projectIds);
}
