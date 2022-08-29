package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    void save(User user);

    void patch(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    List<User> getAllUsers();

}
