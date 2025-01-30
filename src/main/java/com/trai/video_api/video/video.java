package com.trai.video_api.video;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
