package ru.mtven.springdemo.service;

import ru.mtven.springdemo.model.Users;

public interface UserService {

    Users create(Users users);

    Users getCurrentUser();

    Iterable<Users> getAll();
}
