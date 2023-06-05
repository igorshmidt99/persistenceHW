package org.example.project.service;

import lombok.RequiredArgsConstructor;
import org.example.project.ProjectMapper;
import org.example.project.dto.ProjectDto;
import org.example.project.dto.ProjectRequestDto;
import org.example.project.model.Project;
import org.example.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service(value = "projectServiceImplRepo")
@RequiredArgsConstructor
public class ProjectServiceImplRepo implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto addProject(ProjectRequestDto dto) {
        String name = dto.getName();
        Project project = projectRepository.addProject(name);
        return ProjectMapper.toDto(project);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.getProjectById(id);
        return ProjectMapper.toDto(project);
    }

    @Override
    public ProjectDto update(ProjectRequestDto dto, Long id) {
        String name = dto.getName();
        Project project = projectRepository.update(name, id);
        return ProjectMapper.toDto(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(id);
    }

    @Override
    public Set<Project> getUserProjectsById(Set<Long> userProjectsIds) {
        return projectRepository.getUserProjectsById(userProjectsIds);
    }
}
