package com.trai.video_api.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trai.video_api.video.Video;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      return new ResponseEntity<User>(this.userService.createUser(user), HttpStatusCode.valueOf(201));
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e);
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lock exception ocurred");
    }

  }

  // get all users
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers(); // Fetch all users from service
    return ResponseEntity.ok(users); // Return 200 OK with the list of all users
  }

  @GetMapping("/{userId}")
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

  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User updateUser) {
    Optional<User> user = userService.getUserById(userId);

    if (user.isPresent()) {
      updateUser.setUserId(userId);
      userService.updateUser(updateUser);
      return ResponseEntity.ok(updateUser);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @DeleteMapping("/{userId}")
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
