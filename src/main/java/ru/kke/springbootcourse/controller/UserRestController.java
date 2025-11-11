package ru.kke.springbootcourse.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kke.springbootcourse.model.UserDto;
import ru.kke.springbootcourse.service.UserService;
import ru.kke.springbootcourse.validation.Create;
import ru.kke.springbootcourse.validation.Update;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto user = userService.getUserById(id);
        ResponseCookie userId = ResponseCookie.from("userId", user.id().toString()).maxAge(600).secure(true).build();
        return ResponseEntity
                .status(OK)
                .header(HttpHeaders.SET_COOKIE, userId.toString())
                .body(user);
    }

    @Validated(Create.class)
    @PostMapping
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(CREATED).body(userService.createUser(userDto));
    }

    @Validated(Update.class)
    @PutMapping
    UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("{id}")
    void deleteUserById(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
