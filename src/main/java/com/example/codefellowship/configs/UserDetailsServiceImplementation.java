package com.example.codefellowship.configs;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    UserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            return applicationUserRepository.findByUsername(username);
        }

    }

