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
}) // creates a table called users with index so that it is quicker to find by
   // username and email as long as the username and emails are unique

public class User {
    @OneToMany(mappedBy = "user") // connects entity user to video one user to many videos

    private List<Video> videos;

    @Id // table columns
    @GeneratedValue(strategy = GenerationType.AUTO) // GeneratedValue UUID - a unique identifier
    private UUID userId;

    @SuppressWarnings("deprecation")
    @NotNull
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
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
    public UUID getUserId() { // returns unique ID
        return this.userId;
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

    public void setEmail(String email) { // updates email- need exception incase wrong email address format? validators
                                         // - research
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) { // updates password - exception to ensure the password is secure?
                                                       // create a custom exception so that "if" password is more than x
                                                       // characters - should this be in the service?
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
