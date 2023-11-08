package ru.itmentor.spring.boot_security.demo.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.DAO.DaoUser;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Service
public class ServiceUserImp implements ServiceUser {

    private final DaoUser daoUser;
    @Autowired
    public ServiceUserImp(DaoUser daoCar) {
        this.daoUser = daoCar;
    }


    @Override
    public List<User> getUsers() {
        return daoUser.getUsers();
    }

    @Override
    public void saveUser(User user) {
    daoUser.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
     daoUser.updateUser(user);
    }

    @Override
    public User getUser(Long id) {
        return daoUser.getUser(id);
    }

    @Override
    public void deleteUser(Long id) {
    daoUser.deleteUser(id);
    }

}


