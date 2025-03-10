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
public class Video  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne // many videos to one user
    @JoinColumn(name = "userId", referencedColumnName = "id") // JPA converts to snake case
    private User user;

    @SuppressWarnings("deprecation")
    @NotNull
    private String title;
    private String tags;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private String videoUrl;

    // constructor
    public Video(User user, String title, String tags, String description, Instant createdAt, Instant updatedAt, String videoUrl) {
        // initialise fields
        this.user = user;
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.videoUrl = videoUrl;
    }

    public Video() {
        this(null, null, null, null, Instant.now(), Instant.now(), null);
    }

    // getters & setters
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID videoId) {
        this.id = videoId;
    }
    public User getUser() { 
        return this.user;
    }

    public User setUser(User user) { 
        return this.user = user;
    }

    public String getTags(String tags) {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getTitle(String title) {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
