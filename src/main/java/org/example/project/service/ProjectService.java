package org.example.project.service;

import org.example.project.dto.ProjectDto;
import org.example.project.dto.ProjectRequestDto;
import org.example.project.model.Project;

import java.util.Set;

public interface ProjectService {
    ProjectDto addProject(ProjectRequestDto dto);
    ProjectDto getProjectById(Long id);
    ProjectDto update(ProjectRequestDto dto, Long id);
    void delete(Long id);
    Set<Project> getUserProjectsById(Set<Long> userProjectsIds);
}
