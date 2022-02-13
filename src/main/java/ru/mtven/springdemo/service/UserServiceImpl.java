package ru.mtven.springdemo.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mtven.springdemo.model.Users;
import ru.mtven.springdemo.repository.UserRepository;

@Service
@Getter
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Users currentUser;

    @Override
    public Users create(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    @Override
    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        currentUser = userRepository.findByLogin(principal.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return currentUser;
    }

    @Override
    public Iterable<Users> getAll() {
        return userRepository.findAll();
    }
}
