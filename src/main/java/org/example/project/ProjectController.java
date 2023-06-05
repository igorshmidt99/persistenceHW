package org.example.project;

import lombok.RequiredArgsConstructor;
import org.example.project.dto.ProjectDto;
import org.example.project.dto.ProjectRequestDto;
import org.example.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public ProjectDto addProject(@RequestBody ProjectRequestDto dto) {
        return service.addProject(dto);
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable Long id) {
        return service.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public ProjectDto update(@RequestBody ProjectRequestDto dto, @PathVariable Long id) {
        return service.update(dto, id);
    }
}