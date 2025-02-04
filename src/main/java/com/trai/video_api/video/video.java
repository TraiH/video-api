package com.trai.video_api.video;

import java.time.Instant;
import java.util.UUID;

import org.antlr.v4.runtime.misc.NotNull;

import com.trai.video_api.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne // many videos to one user
    @JoinColumn(name = "user_id") // JPA conves to snake case
    private User user;

    @SuppressWarnings("deprecation")
    @NotNull
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private String videoUrl; // temporary signed S3 URL

    // constructor
    public Video(User user, String description, Instant createdAt, Instant updatedAt, String videoUrl) {
        // initialise fields
        this.user = user;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.videoUrl = videoUrl;
    }

    // getters & setters
    public UUID getId() {
        return this.id;
    }

    public User getUser() { // is this correct to return the userId from the User entity?
        return this.user;
    }

    public User setUser(User user) { // is this correct? Do I need to have a User user?
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
