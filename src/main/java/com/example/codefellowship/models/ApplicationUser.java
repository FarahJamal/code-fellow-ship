package com.example.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
public class ApplicationUser implements UserDetails {
    /**username,
     * password (will be hashed using BCrypt),
     * firstName,
     * lastName,
     * dateOfBirth,
     * bio,
     * and any other fields you think are useful.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;

    @Column(columnDefinition = "Text")
    private  String bio;


    private String password;
    private String dateOfBirth;
    private String body;
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ApplicationUser() {

    }

//    public ApplicationUser(String firstName, String lastName, String email, String username, String bio, String password, Date dateOfBirth) {
//        super();
//
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.bio = bio;
//        this.password = password;
//        this.dateOfBirth = dateOfBirth;
//    }

    public ApplicationUser(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}