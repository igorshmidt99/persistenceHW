package org.example.project;

import org.example.project.dto.ProjectDto;
import org.example.project.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public final class ProjectMapper {

    public static ProjectDto toDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }

    public static Project toProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        while (resultSet.next()) {
            project.setId(resultSet.getLong(1));
            project.setName(resultSet.getString(2));
        }
        return project;
    }

    public static Set<String> toProjectsStrings(ResultSet resultSet) throws SQLException {
        Set<String> projects = new HashSet<>();
        while (resultSet.next()) {
            projects.add(resultSet.getString(1));
        }
        return projects;
    }
}
