package ru.itmentor.spring.boot_security.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class DaoRoleImp implements UserDetailsService {
    private UserDetails currentUserDetails;

    private final DaoRole daoRole;

    @Autowired
    public DaoRoleImp(DaoRole daoRole) {
        this.daoRole = daoRole;
    }

    public void setUserRoles(Long user_id, Set<Role> newRoles) {
        try {
            User user = daoRole.getOne(user_id);
            user.setRoles(newRoles);
        } catch (EntityNotFoundException ex) {
            System.out.println("not found");
        }
    }

    public void removeRoles(Long user_id, Set<Role> rolesToRemove) {
        try {
            System.out.println("Попытка загрузить пользователя по email: " + user_id);
            User user = daoRole.getOne(user_id);
            System.out.println("Пользователь загружен: " + user);
            Set<Role> userRoles = user.getRoles();
            System.out.println("Роли пользователя до удаления: " + userRoles);

            userRoles.removeAll(rolesToRemove);

            daoRole.save(user);

        } catch (EntityNotFoundException e) {
            System.out.println("User not found");
        }
    }

    public void deleteById(Long id) {
        daoRole.deleteById(id);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Попытка загрузить пользователя по email: " + email);
        Optional<User> userOptional = daoRole.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        User user = userOptional.get();
        System.out.println("Пользователь найден: " + user.getEmail());

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        currentUserDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                authorities
        );
        return currentUserDetails;

    }

    public User getInfo(UserDetails currentUserDetails) {

        String loggedInUserEmail = currentUserDetails.getUsername();
        Optional<User> userOptional = daoRole.findByEmail(loggedInUserEmail);
        userOptional.isPresent();
        User user = userOptional.get();


        return user;
    }
}


