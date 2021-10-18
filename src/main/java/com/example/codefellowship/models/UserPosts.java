package com.example.codefellowship.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

public class UserPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @CreationTimestamp
    String body;
    LocalDateTime createdAt;

    @ManyToOne
    ApplicationUser applicationUser;

    public UserPosts(String body, LocalDateTime createdAt) {
        this.body = body;
        this.createdAt =createdAt;
    }

    public UserPosts(){}

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserPosts(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
