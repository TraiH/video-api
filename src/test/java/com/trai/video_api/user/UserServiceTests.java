package com.trai.video_api.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Claire", "Timmons", "claire23", "claire.timmons@testexample.com", "hashedPassword",
                Instant.now(), Instant.now());
        System.out.println(user.getUserId());
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("Claire", users.get(0).getFirstName());
    }

    @Test
    void testGetUserById_UserExists() {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(user.getUserId());
        assertTrue(foundUser.isPresent());
        assertEquals("Claire", foundUser.get().getFirstName());
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Optional<User> foundUser = userService.getUserById(UUID.randomUUID());
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("Claire", createdUser.getFirstName());
    }

    @Test
    void testDeleteUser_Success() {
        UUID userId = user.getUserId();
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);
        assertDoesNotThrow(() -> userService.deleteUser(userId));
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_Failure() {
        UUID userId = user.getUserId();
        when(userRepository.existsById(userId)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> userService.deleteUser(userId));
    }
}
