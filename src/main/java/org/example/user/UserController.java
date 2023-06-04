package org.example.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserRequestDto dto) {
        return service.addUser(dto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody UserRequestDto dto, @PathVariable Long id) {
        return service.updateUser(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @PostMapping("/{userId}/{projectId}")
    public UserDtoWithProjects addProject(@PathVariable Long userId, @PathVariable Long projectId) {
        return service.addProjectToUser(userId, projectId);
    }

    @GetMapping("/project/{userId}")
    public UserDtoWithProjects getUserWithProjects(@PathVariable Long userId) {
        return service.getUserWithProjects(userId);
    }

    @DeleteMapping("/{userId}/{projectId}")
    public void deleteUserFromProject(@PathVariable Long userId, @PathVariable Long projectId) {
        service.deleteUserFromProject(userId, projectId);
    }
}