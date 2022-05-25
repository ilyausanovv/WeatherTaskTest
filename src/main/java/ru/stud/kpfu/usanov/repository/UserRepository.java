package ru.stud.kpfu.usanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDto findByEmail(String email);
}