package ru.kke.springbootcourse.service;

import java.util.List;
import ru.kke.springbootcourse.model.UserDto;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Integer id);

}
