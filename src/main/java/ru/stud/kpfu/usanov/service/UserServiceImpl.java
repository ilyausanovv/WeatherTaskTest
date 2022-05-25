package ru.stud.kpfu.usanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stud.kpfu.usanov.dto.CreateUserDto;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.helper.PasswordHelper;
import ru.stud.kpfu.usanov.model.User;
import ru.stud.kpfu.usanov.repository.UserRepository;
import ru.stud.kpfu.usanov.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        return user;
    }

    @Override
    public UserDto getById(Integer id) {
        User user = userRepository.findById(id).orElse(new User("ilya",
                "gamer@mail.ru", "111", Collections.emptyList()));
        return UserDto.fromModel(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public UserDto save(CreateUserDto user) {
        return UserDto.fromModel(userRepository.save(new User(user.getName(), user.getEmail(),
                PasswordHelper.encrypt(user.getPassword()), Collections.emptyList())));
    }
}