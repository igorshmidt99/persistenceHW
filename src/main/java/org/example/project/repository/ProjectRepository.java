package org.example.project.repository;

import org.example.project.model.Project;

import java.util.Set;

public interface ProjectRepository {
    Project addProject(String name);
    Project getProjectById(Long id);
    Project update(String name, Long id);
    void delete(Long id);
    Set<Project> getUserProjectsById(Set<Long> projectIds);
}
