package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.UserPosts;
import com.example.codefellowship.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class PostsController {

    @Autowired
    UserRepository applicationUserRepository;

    @Autowired
    PostsRepositoty postRepository;

    @PostMapping("/newpost")
    public String addpost(@RequestParam (value ="body") String body,Model model, Principal principal){

        UserPosts post = new UserPosts(body);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApplicationUser newPost = applicationUserRepository.findByUsername(principal.getName());

        post.setApplicationUser(applicationUserRepository.findByUsername(userDetails.getUsername()));
        model.addAttribute("posts",newPost);

        postRepository.save(post);

        return("redirect:/newpost");

    }
    @GetMapping("/newpost")
    public String show(Model model,UserPosts post){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("posts",post.getBody());
        model.addAttribute("user", postRepository.findAll());

        return ("addPost");
    }
}


