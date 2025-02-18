package com.trai.video_api.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  public final UserRepository userRepository;

  // initialise the repository dependency (dependency injection) so that the
  // service layer can communicate with the database
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // returns all users
  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }

  public Optional<User> getUserById(UUID userId) {
    return userRepository.findById(userId);
  }

  public User createUser(User user) throws IllegalArgumentException, OptimisticLockingFailureException {
    try {
      return userRepository.save(user);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid user data: " + e.getMessage(), e);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while saving the user.", e);
    }
  }

  // update the user
  public User updateUser(User updatedUser) {
    if (userRepository.existsById(updatedUser.getUserId())) {
      try {
        return userRepository.save(updatedUser);
      } catch (OptimisticLockingFailureException e) {
        throw new OptimisticLockingFailureException("Conflict while updating the user.", e);
      } catch (DataAccessException e) {
        throw new RuntimeException("Database error while updating the user.", e);
      }
    } else {
      throw new NoSuchElementException("User not found with ID: " + updatedUser.getUserId());
    }
  }

  public List<User> getUserByFirstName(String firstName) {
    return this.userRepository.findByFirstName(firstName);
  }

  public List<User> getUserByLastName(String lastName) {
    return this.userRepository.findByLastName(lastName);
  }

  public Optional<User> getUserByUsername(String userame) {
    return this.userRepository.findByUsername(userame);
  }

  public Optional<User> getUserByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  // add to service for username so it is unique if present

  // Delete user by ID
  public void deleteUser(UUID userId) {
    if (userRepository.existsById(userId)) {
        try {
            userRepository.deleteById(userId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while deleting user ID: " + userId, e);
        }
    } else {
        throw new NoSuchElementException("User not found with ID: " + userId);
    }
}
}