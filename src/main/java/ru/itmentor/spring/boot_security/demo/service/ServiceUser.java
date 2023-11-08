package ru.itmentor.spring.boot_security.demo.service;


import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface ServiceUser {
    List<User> getUsers();
    void saveUser(User user);
  void updateUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);


}