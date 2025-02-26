package com.trai.video_api.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.trai.video_api.VideoApiApplication;
import com.trai.video_api.VideoApiApplicationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest(classes = VideoApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTest {
    
@LocalServerPort
	private int port;

	private URI baseURI;

	@Autowired
	private TestRestTemplate restTemplate;

    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user = new User ("Claire", "Timmons", "claire23", "claire.timmons@testexample.com", "hashedPassword", Instant.now(), Instant.now(), "profilePicUrl");
    private List<User> defaultUsers = new ArrayList<>(){    
        {
            add(new User("Claire", "Timmons", "claire23", "claire.timmons@testexample.com", "hashedPassword",
        Instant.now(), Instant.now(), "profilePicUrl"));
            add(new User("Tom", "Riley", "Tom48", "tom.riley@testexample.com", "hashedPassword",
        Instant.now(), Instant.now(), "profilePicUrl"));
        }
    };
    

    @BeforeEach
    void setUp() throws RuntimeException {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        
        this.baseURI = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("api/v1/users")
                .build()
                .toUri();
                user = new User("Claire", "Timmons", "claire23", "claire.timmons@testexample.com", "hashedPassword",
                Instant.now(), Instant.now(), "profilePicUrl");
        ReflectionTestUtils.setField(user, "id", UUID.randomUUID());

        defaultUsers = List.of(user);
        when(userService.getAllUsers()).thenReturn(defaultUsers);
        when(userService.getUserById(user.getUserId())).thenReturn(Optional.of(user));
    }
    
    @Test
    void testGetUserByUserId_UserExists() throws Exception {
        when(userService.getUserById(user.getUserId())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/v1/users/" + user.getUserId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Claire"));
    }

    @Test
    void testGetUserByUserId_UserNotFound() throws Exception {
        when(userService.getUserById(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/users/" + UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser_Success() throws Exception {
        when(userService.createUser(any(User.class))).thenAnswer(invocation -> setUserId(invocation.getArgument(0)));

        // Act
        ResponseEntity<User> response = restTemplate.postForEntity(baseURI.toString(), user, User.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getUserId());
        verify(userService).createUser(any(User.class));
    }

    @Test
    void testDeleteUser_Success() throws Exception {
        doNothing().when(userService).deleteUser(user.getUserId());

        mockMvc.perform(delete("/api/v1/users/" + user.getUserId()))
            .andExpect(status().isNoContent());
    }
    
    private static User setUserId(User user) {
        ReflectionTestUtils.setField(user, "id", UUID.randomUUID());
        return user;
      }
}
    
