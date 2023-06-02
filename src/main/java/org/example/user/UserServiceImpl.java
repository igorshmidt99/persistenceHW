package org.example.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserDto getUserById(Long id) {
        User user = userDao.getUserById(id);
        return UserDto.builder().name(user.getName()).position("lol").build();
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userDao.addUser(userDto.getName(), 1L);
        return userDto;
    }

    @Override
    public void deleteUser(String name) {
        userDao.deleteUser(name);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDto updateUser(UserDto dto, Long id) {
        String name = dto.getName();
        String position = dto.getPosition();
        if (name != null && position != null) {
//            userDao.updateUser()
        }
//        return userDao.updateUser();
        return null;
    }

}