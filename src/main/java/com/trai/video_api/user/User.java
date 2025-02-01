package com.trai.video_api.user;

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
@Table(name = "users") //creates a table called users
public class User {

    @Id //table columns
    @GeneratedValue(strategy=GenerationType.IDENTITY) //GeneratedValue UUID - an identifier generates an ID which is going to be unique across the database
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Instant createdAt;
    private Instant updatedAt;
    private String profilePicture;//link to their picture store in s3
    private String passwordHash;

}
