package org.example.project.service;

import lombok.RequiredArgsConstructor;
import org.example.project.ProjectMapper;
import org.example.project.dao.ProjectDao;
import org.example.project.dto.ProjectDto;
import org.example.project.dto.ProjectRequestDto;
import org.example.project.model.Project;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectDao.getProjectById(id);
        return ProjectMapper.toDto(project);
    }

    @Override
    public ProjectDto addProject(ProjectRequestDto dto) {
        String name = dto.getName();
        Project project = projectDao.addProject(name);
        return ProjectMapper.toDto(project);
    }

    @Override
    public ProjectDto update(ProjectRequestDto dto, Long id) {
        String name = dto.getName();
        Project project = projectDao.update(name, id);
        return ProjectMapper.toDto(project);
    }

    @Override
    public void delete(Long id) {
        projectDao.delete(id);
    }

    @Override
    public Set<String> getUserProjectsById(Set<Long> userProjectsIds) {
        return projectDao.getUserProjectsById(userProjectsIds);
    }
}
