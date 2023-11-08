package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.itmentor.spring.boot_security.demo.DAO.UserRepository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Set;

public interface RepositoryService {

    void setUserRoles(Long userId, Set<Role> newRoles);
    void removeRoles(Long userId, Set<Role> rolesToRemove);
    UserDetails loadUserByUsername(String email);
    void deleteById(Long id);
    User getInfo(UserDetails currentUserDetails);

}
