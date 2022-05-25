package ru.stud.kpfu.usanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.stud.kpfu.usanov.dto.CreateUserDto;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.helper.PasswordHelper;
import ru.stud.kpfu.usanov.model.User;
import ru.stud.kpfu.usanov.repository.UserRepository;
import ru.stud.kpfu.usanov.service.UserService;

import java.util.stream.Collectors;
import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto get(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    @ResponseBody
    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
        return userService.save(user);
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto) {
        System.out.println(userDto);
        userService.save(userDto);

        return "sign_up_success";
    }

    @GetMapping("/error")
    public String getLoginFail() {
        return "login_fail";
    }
}