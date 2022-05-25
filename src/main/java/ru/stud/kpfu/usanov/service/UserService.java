package ru.stud.kpfu.usanov.service;

import ru.stud.kpfu.usanov.dto.CreateUserDto;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.model.User;

import java.util.List;

public interface UserService {
    User getByEmail(String email);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    UserDto save(CreateUserDto createUserDto);
}