package com.trai.video_api.video;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trai.video_api.user.User;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoServiceTest {
  @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    private Video testVideo;
    private UUID videoId;

    @Mock
    private User user;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        videoId = UUID.randomUUID();
        testVideo = new Video(user, "Test Video", "Tag1,Tag2", "Test Description", Instant.parse("2024-04-28T14:30:00Z"), Instant.parse("2025-02-01T14:30:00Z"), "http://example.com/video.mp4");
    }

    @Test
    void shouldReturnVideoWhenExists() {
        when(videoRepository.findById(videoId)).thenReturn(Optional.of(testVideo));

        Optional<Video> foundVideo = videoService.getVideoById(videoId);

        assertThat(foundVideo).isPresent();
        assertThat(foundVideo.get().getTitle("interesting title")).isEqualTo("Test Video");
    }

    @Test
    void shouldReturnEmptyWhenVideoNotFound() {
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());

        Optional<Video> foundVideo = videoService.getVideoById(videoId);

        assertThat(foundVideo).isEmpty();
    }
}  

