package org.example.user.service;

import lombok.RequiredArgsConstructor;
import org.example.position.model.Position;
import org.example.position.repository.PositionRepository;
import org.example.user.UserMapper;
import org.example.user.dto.UserDto;
import org.example.user.dto.UserDtoWithProjects;
import org.example.user.dto.UserRequestDto;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.example.user.user_projects.UserProjectDao;
import org.springframework.stereotype.Service;

@Service(value = "userServiceImplRepo")
@RequiredArgsConstructor
public class UserServiceImplRepo implements UserService {

    private final UserRepository userRepository;
    private final PositionRepository positionRepository;
    private final UserProjectDao userProjectDao;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getUserById(id);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto addUser(UserRequestDto userDto) {
        String name = userDto.getName();
        Position position = getPositionIfExist(userDto);
        User user = userRepository.addUser(name, position);
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public UserDto updateUser(UserRequestDto dto, Long id) {
        User user = userRepository.getUserById(id);
        updateUser(user, dto);
        user = userRepository.updateUser(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDtoWithProjects addProjectToUser(Long userId, Long projectId) {
        userProjectDao.addProjectToUser(userId, projectId);
        return getUserWithProjects(userId);
    }

    @Override
    public UserDtoWithProjects getUserWithProjects(Long userId) {
        User user = userRepository.getUserById(userId);
        return UserMapper.toDtoWithProjects(user);
    }

    @Override
    public void deleteUserFromProject(Long userId, Long projectId) {
        userProjectDao.deleteUserFromProject(userId, projectId);
    }

    private Position getPositionIfExist(UserRequestDto dto) {
        String grade = dto.getPosition();
        Position position = null;
        if (grade != null) {
            position = positionRepository.getByGrade(grade);
        }
        return position;
    }

    private void updateUser(User user, UserRequestDto dto) {
        String name = dto.getName();
        Position position = getPositionIfExist(dto);
        if (name != null) user.setName(name);
        if (position != null) user.setPosition(position);
    }
}
