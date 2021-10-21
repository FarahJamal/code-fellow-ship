package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("api/")
public class ApplicationUserController {
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository applicationUserRepository;


    @PostMapping("/applicationuser")
    public String createUser(@RequestBody ApplicationUser applicationUser) throws URISyntaxException {

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUserRepository.save(applicationUser);
        //return ResponseEntity.created(new URI("http://localhost:3001/api/users" + savedUser.getId())).body(savedUser);
        return "redirect:http://localhost:8089/api/users";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @GetMapping("/profile")
    public String getProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());

        if (applicationUser == null)
            return "redirect:http://localhost:8089/api/users";

        model.addAttribute("user", applicationUser);
        return "";
    }
}
