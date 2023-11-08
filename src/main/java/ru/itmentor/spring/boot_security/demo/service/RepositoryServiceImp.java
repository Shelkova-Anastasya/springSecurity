package ru.itmentor.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.DAO.UserRepositoryImp;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Set;

@Service
public class RepositoryServiceImp implements RepositoryService{

private final UserRepositoryImp userRepositoryImp;
    @Autowired
    public RepositoryServiceImp(UserRepositoryImp userRepositoryImp) {
        this.userRepositoryImp = userRepositoryImp;
    }

    @Override
    public void setUserRoles(Long userId, Set<Role> newRoles) {
        userRepositoryImp.setUserRoles(userId, newRoles);

    }
    public void removeRoles(Long userId, Set<Role> rolesToRemove){
        userRepositoryImp.removeRoles(userId, rolesToRemove);
    }
    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepositoryImp.loadUserByUsername(email);
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryImp.deleteById(id);
    }

    public User getInfo(UserDetails currentUserDetails){
        return userRepositoryImp.getInfo(currentUserDetails);
    }
}
