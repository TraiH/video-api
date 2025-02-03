package com.trai.video_api.user;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

//entity class to represent users who interact with the video API
// This tells Hibernate to make a table out of this class
@Entity
@Table(name = "users") // creates a table called users
public class User {
    @Id // table columns
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue UUID - a unique identifier
    private UUID id; // do I need to include this in the constructor?
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String passwordHash;
    private Instant createdAt;
    private Instant updatedAt;
    private String profilePicture;// link to their picture store in s3

    // constructor
    public User(String firstName, String lastName, String username, String email, String passwordHash,
            Instant createdAt, Instant updatedAt, String profilePicture) {
        // initialise fields
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profilePicture = profilePicture;
    }

    // getters and setters
    public UUID getID() { // returns unique ID
        return this.id;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName; // Return full name- do I need to get First and Last name first?
    }

    public String getUsername() { // returns username
        return this.username;
    }

    public void setUsername(String username) { // updates username - exception if there is already a username with the
                                               // same username?
        this.username = username;
    }

    public String getEmail() { // returns email address - need exception incase wrong email address format?
        return this.email;
    }

    public void setEmail(String email) { // updates email- need exception incase wrong email address format?
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) { // updates password - exception to ensure the password is secure?
        this.passwordHash = passwordHash;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
