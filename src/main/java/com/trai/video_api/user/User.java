package com.trai.video_api.user;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.antlr.v4.runtime.misc.NotNull;

import com.trai.video_api.video.Video;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;

//entity class to represent users who interact with the video API
// This tells Hibernate to make a table out of this class
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_username", columnList = "username", unique = true),
        @Index(name = "idx_email", columnList = "email", unique = true)
}) 

public class User {
    @OneToMany(mappedBy = "user")

    private List<Video> videos;

    @Id // table columns
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @SuppressWarnings("deprecation")
    @NotNull
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    private Instant createdAt;
    private Instant updatedAt;
    

    // constructor
    public User(String firstName, String lastName, String username, String email, String password,
            Instant createdAt, Instant updatedAt) {
        // initialise fields
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        
    }

    public User() {
        this(null, null, null, null, null, Instant.now(), Instant.now());
    }

    // getters and setters
    public UUID getUserId() {
        return this.id;
    }

    public void setUserId(UUID userId) { 
        this.id = userId;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getUsername() { 
        return this.username;
    }

    public void setFirstName(String firstName) { 
        this.firstName = firstName;
    }

    public void setLastName(String lastName) { 
        this.lastName = lastName;
    }
    public void setUsername(String username) { 
        this.username = username;
    }

    public String getEmail() { 
        return this.email;
    }

    public void setEmail(String email) { 
        this.email = email;
    }

    public String getPassword() { 
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

}
