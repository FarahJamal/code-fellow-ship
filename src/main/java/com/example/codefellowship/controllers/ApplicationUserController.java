package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("api/")
public class ApplicationUserController {
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository applicationUserRepository;


    @PostMapping("/applicationuser")
    public ResponseEntity createUser(@RequestBody ApplicationUser applicationUser) throws URISyntaxException {

  applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        ApplicationUser savedUser=applicationUserRepository.save(applicationUser);
        return ResponseEntity.created(new URI("/applicationuser/" + savedUser.getId())).body(savedUser);
    }



//    @GetMapping("/login")
//    public String showLoginPage(){
//        return "login.html";
//    }
//
//    @GetMapping("/index")
//    public String showSignUpPage(){
//        return "index.html";
//    }
//
//    @GetMapping("/applicationusers")
//    public String showUserPage(){
//        return "applicationusers.html";
//    }
}
