package org.example.user;

import lombok.RequiredArgsConstructor;
import org.example.position.PositionDto;
import org.example.position.PositionService;
import org.example.project.ProjectService;
import org.example.user.user_projects.UserProjectDao;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PositionService positionService;
    private final UserProjectDao userProjectDao;
    private final ProjectService projectService;

    @Override
    public UserDto getUserById(Long id) {
        User user = userDao.getUserById(id);
        Long positionId = user.getPosition();
        PositionDto positionDto = null;

        if (positionId != null) {
            positionDto = positionService.getById(positionId);
        }
        return UserMapper.toDto(user, positionDto);
    }

    @Override
    public UserDto addUser(UserRequestDto dto) {
        String grade = dto.getPosition();
        String name = dto.getName();
        PositionDto positionDto = null;
        Long positionId = null;

        if (grade != null) {
            positionDto = positionService.getByGrade(grade);
            positionId = positionDto.getId();
        }
        User user = userDao.addUser(name, positionId);
        return UserMapper.toDto(user, positionDto);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDto updateUser(UserRequestDto dto, Long id) {
        String name = dto.getName();
        String grade = dto.getPosition();

        if (name != null || grade != null) {
            Long positionId;
            PositionDto positionDto = null;
            User user;
            if (grade != null && name != null) {
                positionDto = positionService.getByGrade(grade);
                positionId = positionDto.getId();
                user = userDao.updateUser(name, positionId, id);
            } else if (grade != null) {
                positionDto = positionService.getByGrade(grade);
                positionId = positionDto.getId();
                user = userDao.updateUser(positionId, id);
            } else {
                user = userDao.updateUser(name, id);
            }
            return UserMapper.toDto(user, positionDto);
        }
        return null;
    }

    @Override
    public UserDtoWithProjects addProjectToUser(Long userId, Long projectId) {
        userProjectDao.addProjectToUser(userId, projectId);
        User user = userDao.getUserById(projectId);
        Set<Long> userProjectsIds = userProjectDao.getUserProjectsId(userId);
        PositionDto positionDto = null;
        Long positionId = user.getPosition();
        if (positionId != null) positionDto = positionService.getById(positionId);
        Set<String> projects = projectService.getUserProjectsById(userProjectsIds);
        return UserMapper.toDtoWithProjects(user, positionDto, projects);
    }

    @Override
    public UserDtoWithProjects getUserWithProjects(Long userId) {
        User user = userDao.getUserById(userId);
        Set<Long> userProjectsIds = userProjectDao.getUserProjectsId(userId);
        Set<String> projects = projectService.getUserProjectsById(userProjectsIds);
        PositionDto positionDto = null;
        Long positionId = user.getPosition();
        if (positionId != null) positionDto = positionService.getById(user.getPosition());
        return UserMapper.toDtoWithProjects(user, positionDto, projects);
    }

    @Override
    public void deleteUserFromProject(Long userId, Long projectId) {
        userProjectDao.deleteUserFromProject(userId, projectId);
    }
}