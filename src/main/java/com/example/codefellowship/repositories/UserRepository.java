package com.example.codefellowship.repositories;

import com.example.codefellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
     ApplicationUser findByUsername(String username);
//     Optional<ApplicationUser> findApplicationUserByUsername(String username);
//     Optional<ApplicationUser> findApplicationUserById(Long id);
}