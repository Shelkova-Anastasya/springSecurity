package ru.itmentor.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.DAO.DaoRoleImp;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Set;

@Service
public class ServiceRoleImp implements ServiceRole {

private final DaoRoleImp daoRoleImp;
    @Autowired
    public ServiceRoleImp(DaoRoleImp daoRoleImp) {
        this.daoRoleImp = daoRoleImp;
    }

    @Override
    public void setUserRoles(Long userId, Set<Role> newRoles) {
        daoRoleImp.setUserRoles(userId, newRoles);

    }
    public void removeRoles(Long userId, Set<Role> rolesToRemove){
        daoRoleImp.removeRoles(userId, rolesToRemove);
    }
    @Override
    public UserDetails loadUserByUsername(String email) {
        return daoRoleImp.loadUserByUsername(email);
    }

    @Override
    public void deleteById(Long id) {
        daoRoleImp.deleteById(id);
    }

    public User getInfo(UserDetails currentUserDetails){
        return daoRoleImp.getInfo(currentUserDetails);
    }
}
