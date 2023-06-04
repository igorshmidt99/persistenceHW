package org.example.project;

import java.util.Set;

public interface ProjectService {
    ProjectDto addProject(ProjectRequestDto dto);
    ProjectDto getProjectById(Long id);
    ProjectDto update(ProjectRequestDto dto, Long id);
    void delete(Long id);
    Set<String> getUserProjectsById(Set<Long> userProjectsIds);
}
