package com.example.codefellowship.controllers;
import java.util.List;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List <ApplicationUser> getUsers() {
        return this.userRepository.findAll();
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}