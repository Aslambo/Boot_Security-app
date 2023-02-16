package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {

    User getUserByName(String username);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

}
