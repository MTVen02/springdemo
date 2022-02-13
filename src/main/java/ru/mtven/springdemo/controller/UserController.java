package ru.mtven.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mtven.springdemo.model.Users;
import ru.mtven.springdemo.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public Users create(@RequestBody Users users) {
        return userService.create(users);
    }

    @GetMapping("/users")
    public Iterable<Users> getAll() {
        return userService.getAll();
    }
}
