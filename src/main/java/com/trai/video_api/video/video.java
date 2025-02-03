package com.trai.video_api.video;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userID; // foreign key to user UUID - Do I need to code anything else to link the
                         // tables?
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private String videoUrl; // temporary signed S3 URL

    // constructor

    // initialise fields

    // getters & setters
}
