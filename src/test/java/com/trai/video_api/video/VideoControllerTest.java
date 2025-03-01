package com.trai.video_api.video;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.trai.video_api.VideoApiApplication;

@SpringBootTest(classes = VideoApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VideoControllerTest {

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
    }

    @Test
    void shouldReturnNotFoundWhenVideoDoesNotExist() throws Exception {
        UUID videoId = UUID.randomUUID();
        when(videoService.getVideoById(videoId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/videos/" + videoId))
                .andExpect(status().isNotFound());
    }
}
