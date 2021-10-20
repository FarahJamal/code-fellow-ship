package com.example.codefellowship.repositories;


import com.example.codefellowship.models.UserPosts;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostsRepositoty extends JpaRepository<UserPosts, Long> {
}