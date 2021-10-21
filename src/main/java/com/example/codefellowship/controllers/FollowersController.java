package com.example.codefellowship.controllers;
import com.example.codefellowship.repositories.PostsRepositoty;
import com.example.codefellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.codefellowship.models.ApplicationUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.*;


@Controller
public class FollowersController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository applicationUserRepository;

    @Autowired
    PostsRepositoty postRepository;
    @GetMapping("/myprofile")
    public String getProfilePage(Principal p, Model m) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser", currentUser);
        m.addAttribute("sessionStatus", true);
        return "profile";
    }
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable long id, Principal p, Model m){
        ApplicationUser searchedUser = applicationUserRepository.findById(id).get();
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        boolean sessionStatus = isLoggedInUserTheSameAsSearchedUser(currentUser, searchedUser);
        boolean followerStatus = isLoggedInUserAlreadyFollowingSearchedUser(currentUser, searchedUser);
        m.addAttribute("currentUser", searchedUser);
        m.addAttribute("sessionStatus", sessionStatus);
        m.addAttribute("followerStatus", followerStatus);

        return "profile";
    }

    @GetMapping("/discover")
    public String showAllTheUsersInDataBase(Principal p, Model m){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        Iterable<ApplicationUser> allUsers = applicationUserRepository.findAll();
        List allUsersList =new ArrayList((Collection) allUsers);
        allUsersList.remove(loggedInUser);
        allUsersList.removeAll(loggedInUser.getFollowing());

        m.addAttribute("allUsers", allUsersList);
        m.addAttribute("currentUser", loggedInUser);
        m.addAttribute("sessionStatus", true);
        return "discover";
    }

    @GetMapping ("/individualUser")
    public String followUser(@RequestParam String username, Principal p, Model m){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser userToFollow = applicationUserRepository.findByUsername(username);
        System.out.println("this is my current user: "+ currentUser.getUsername());
        System.out.println("this is the user I want to follow: "+ userToFollow.getUsername());
        currentUser.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(currentUser);
        applicationUserRepository.save(currentUser);
        applicationUserRepository.save(userToFollow);
        m.addAttribute("currentUser", currentUser);
        return ("redirect:/myprofile");
    }


    @GetMapping("/feed")
    public String getPostFeed(Principal p, Model m) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("following", currentUser.getFollowing());
        m.addAttribute("currentUser", currentUser);
        m.addAttribute("sessionStatus", true);
        return "feed";
    }

    public Boolean isUserLoggedIn(Principal p){
        if(p != null)
            return true;
        else
            return false;
    }

    public Boolean isLoggedInUserTheSameAsSearchedUser(ApplicationUser currentUser, ApplicationUser targetUser) {
        if(currentUser.getUsername().equals(targetUser.getUsername()))
            return true;
        else
            return false;
    }

    public Boolean isLoggedInUserAlreadyFollowingSearchedUser(ApplicationUser currentUser, ApplicationUser searchedUser){
        if(currentUser.getFollowing().contains(searchedUser))
            return true;
        else
            return false;
    }
    }

