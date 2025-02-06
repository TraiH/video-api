package com.trai.video_api.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trai.video_api.video.Video;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Fetch all users from service
        return ResponseEntity.ok(users); // Return 200 OK with the list of all users
    }

    @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable UUID userId) {
    Optional<User> user = userService.getUserById(userId);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get()); // 200 OK
    } else {
      return ResponseEntity.notFound().build(); // 404 Not Found
    }
  }

    // get user by first name
    @GetMapping("/first-name")
    public ResponseEntity<User> getUser(@PathVariable String firstName) {
        Optional<User> user = userService.getUserByFirstName(firstName);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // 200 OK
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/last-name")
    public ResponseEntity<User> getUserLastName(@PathVariable String lastName) {
        Optional<User> user = userService.getUserByLastName(lastName);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // 200 OK
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/username")
    public ResponseEntity<User> getUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // 200 OK
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/email")
    public ResponseEntity<User> getEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // 200 OK
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable UUID userId) {
    Optional<User> user = userService.getUserById(userId);

    if (user.isPresent()) {
      userService.deleteUser(userId); 
      return ResponseEntity.noContent().build(); // Return 204 No Content (successful deletion)
    } else {
      return ResponseEntity.notFound().build(); // Return 404 Not Found
    }
  }
}
