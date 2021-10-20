package com.example.codefellowship.controllers;


import com.example.codefellowship.models.ApplicationUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller

public class ApplicationController {

    @GetMapping("/")
    public String showHomePage(Principal p){
        System.out.println("p" + p);
        if(p != null){
            System.out.println("p.getName() = " + p.getName());
        }
        return "splash";
    }
    @GetMapping("/*")
    public String catchAll(){
        return "userprofile";
    }

}
