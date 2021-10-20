package com.example.codefellowship.controllers;

import com.example.codefellowship.models.UserPosts;
import com.example.codefellowship.repositories.PostsRepositoty;
import com.example.codefellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class FollowersController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository applicationUserRepository;

    @Autowired
    PostsRepositoty postRepository;

    @GetMapping("/profile/{id}")
    public String getHome(Model m, @PathVariable("id") Long id){
        Object princpal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(princpal instanceof UserDetails){
            String username=((UserDetails)princpal).getUsername();
            m.addAttribute("username",username);
            ApplicationUser pro=applicationUserRepository.findById(id).get();
            List<UserPosts> post=pro.getPosts();
            m.addAttribute("postses",post);
        }else {
            String username=princpal.toString();
        }
        m.addAttribute("username",applicationUserRepository.findById(id).get());
        return "users";
    }

    @PostMapping("/following")
    public String showFollowers(String firstName, String lastName, String url){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstName(firstName);
        applicationUser.setLastName(lastName);
        applicationUser.setUrl(url);
        applicationUserRepository.save(applicationUser);
        return ("redirect:/following");
    }
    @GetMapping("/following")
    public String show(Model model,UserPosts post){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("posts",post.getBody());
        model.addAttribute("user", postRepository.findAll());

        return ("userprofile");
    }
    @PostMapping("/followersFeed")
    public String giveFollowers(long giverId, long receiverId){
        ApplicationUser giver = applicationUserRepository.findById(giverId).get();
        ApplicationUser receiver = applicationUserRepository.findById(receiverId).get();

        giver.getUsersFollowReceived().add(receiver);

        applicationUserRepository.save(receiver);

        return ("redirect:/following");
    }

    }

