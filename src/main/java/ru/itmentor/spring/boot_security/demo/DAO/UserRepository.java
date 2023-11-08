package ru.itmentor.spring.boot_security.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;


import java.util.Optional;
import java.util.Set;



public interface UserRepository extends JpaRepository<User, Long> {

 Optional<User> findByEmail(String email);



}


