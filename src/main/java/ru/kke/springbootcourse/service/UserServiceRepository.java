package ru.kke.springbootcourse.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.kke.springbootcourse.mapper.UserMapper;
import ru.kke.springbootcourse.model.UserDto;
import ru.kke.springbootcourse.model.entity.AddressEntity;
import ru.kke.springbootcourse.model.entity.UserEntity;
import ru.kke.springbootcourse.repository.AddressRepository;
import ru.kke.springbootcourse.repository.UserRepository;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class UserServiceRepository implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Integer id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        log.info("user from repo {}", user);
        AddressEntity address = user.getAddress();
        log.info("user from address {}", address.getUser());

        log.info("groups from user ={}", user.getGroups());
        user = userRepository.findById(id).orElse(null);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return Optional.of(userDto)
                .map(u -> {
                    log.info("user from repo before save {}", u);
                    return userMapper.toEntity(userDto);
                })
                .map(u -> {
                    UserEntity user = userRepository.save(u);
                    log.info("user from repo after save {}", user);
                    return user;

                })
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("user create error!"));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return userRepository.findById(userDto.id())
                .map(user -> user
                        .setName(userDto.username())
                        .setEmail(userDto.email()))
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("user not found!"));
    }

    @Override
    public void deleteUser(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found!"));
        log.info("user from repo before delete {}", user);
        userRepository.delete(user);
        log.info("user from repo after delete {}", user);
    }
}
