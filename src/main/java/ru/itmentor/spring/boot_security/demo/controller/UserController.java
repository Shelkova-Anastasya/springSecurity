package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.DAO.UserRepositoryImp;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RepositoryService;

@RequestMapping("/user")
@Controller
public class UserController {


    private final RepositoryService repositoryService;
    @Autowired
    public UserController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }



    @GetMapping
    public String getUserInfo(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    if (userDetails != null) {
        User user = repositoryService.getInfo(userDetails); // Получить информацию о пользователе с использованием getInfo

        if (user != null) {
            model.addAttribute("username", user.getEmail());
            model.addAttribute("user", user);
        }
    }
    return "info";
    }
}

