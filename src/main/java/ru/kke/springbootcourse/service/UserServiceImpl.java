package ru.kke.springbootcourse.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kke.springbootcourse.model.UserDto;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Integer, UserDto> userRepository = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        int id = sequenceId.getAndIncrement();
        userRepository.put(id, UserDto.builder().id(id).username("username1").email("email1").build());
        id = sequenceId.getAndIncrement();
        userRepository.put(id, UserDto.builder().id(id).username("username2").email("email2").build());
        id = sequenceId.getAndIncrement();
        userRepository.put(id, UserDto.builder().id(id).username("username3").email("email3").build());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.copyOf(userRepository.values());
    }

    @Override
    public UserDto getUserById(@PathVariable Integer id) {
        return userRepository.get(id);
    }

    @Override
    public UserDto createUser(@RequestBody UserDto userDto) {
        int id = sequenceId.getAndIncrement();
        UserDto newUser = UserDto.builder().id(id).username(userDto.username()).email(userDto.email()).build();
        userRepository.put(id, newUser);
        return newUser;
    }

    @Override
    public UserDto updateUser(@RequestBody UserDto userDto) {
        UserDto user = userRepository.get(userDto.id());
        if (user == null) {
            return null;
        }
        UserDto newUser = UserDto.builder().id(userDto.id()).username(userDto.username()).email(userDto.email()).build();
        return userRepository.put(user.id(), newUser);
    }

    @Override
    public void deleteUser(@PathVariable Integer id) {
        userRepository.remove(id);
    }
}
