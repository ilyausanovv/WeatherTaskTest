package ru.stud.kpfu.usanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stud.kpfu.usanov.dto.CreateUserDto;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.helper.PasswordHelper;
import ru.stud.kpfu.usanov.model.User;
import ru.stud.kpfu.usanov.repository.UserRepository;

import java.util.stream.Collectors;
import javax.validation.Valid;


@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public Iterable<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(new User("ilya",
                "gamer@mail.ru", "111"));
        return UserDto.fromModel(user);
    }

    @PostMapping("/user")
    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
        return UserDto.fromModel(userRepository.save(new User(user.getName(), user.getEmail(),
                PasswordHelper.encrypt(user.getPassword()))));
    }
}