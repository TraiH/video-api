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
    return ResponseEntity.ok(users); 
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUser(@PathVariable UUID userId) {
    try {
      Optional<User> user = userService.getUserById(userId);

      if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
      } else {
        return ResponseEntity.notFound().build(); // 404 Not Found
      }
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Invalid data:  Please check the user details and try again.", e);
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Update conflict occurred", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
    }
  }

  // get user by first name
  @GetMapping("/first-name/{firstName}")
  public ResponseEntity<User>getUserFirstName(@PathVariable String firstName) {
    List<User> user = userService.getUserByFirstName(firstName);

    if (!user.isEmpty()) {
      return ResponseEntity.ok(user.get(0)); // 200 OK
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/last-name/{lastName}")
  public ResponseEntity<User>getUserLastName(@PathVariable String lastName) {
    List<User> user = userService.getUserByLastName(lastName);

    if (!user.isEmpty()) {
      return ResponseEntity.ok(user.get(0)); 
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @GetMapping("/username/{username}")
  public ResponseEntity<User> getUsername(@PathVariable String username) {
    Optional<User> user = userService.getUserByUsername(username);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get()); 
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @GetMapping("/email/{email}")
  public ResponseEntity<User> getEmail(@PathVariable String email) {
    Optional<User> user = userService.getUserByEmail(email);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get()); 
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User updateUser) {
    try {
      Optional<User> user = userService.getUserById(userId);

      if (user.isPresent()) {
        updateUser.setUserId(userId);
        userService.updateUser(updateUser);
        return ResponseEntity.ok(updateUser);
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Update conflict occurred", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user", e);
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
    try {
      Optional<User> user = userService.getUserById(userId);

      if (user.isPresent()) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build(); 
      } else {
        return ResponseEntity.notFound().build(); 
      }
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user", e);
    }
  }
}
