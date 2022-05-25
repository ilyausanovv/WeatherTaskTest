package ru.stud.kpfu.usanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.stud.kpfu.usanov.model.User;
import ru.stud.kpfu.usanov.repository.UserRepository;
import ru.stud.kpfu.usanov.security.CustomUserDetails;


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        return CustomUserDetails.fromModel(user);
    }
}
