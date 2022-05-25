package ru.stud.kpfu.usanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stud.kpfu.usanov.dto.CreateUserDto;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.helper.PasswordHelper;
import ru.stud.kpfu.usanov.model.User;
import ru.stud.kpfu.usanov.repository.UserRepository;
import ru.stud.kpfu.usanov.service.UserService;

import java.util.stream.Collectors;
import javax.validation.Valid;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
        return userService.save(user);
    }
}