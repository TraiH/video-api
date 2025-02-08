package com.trai.video_api.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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
        this.userRepository.save(user);
        return user;
    }
  public void updateUser(User updateUser) {
    userRepository.save(updateUser); // Use save() to update
}

  public Optional<User> getUserByFirstName(String firstName) {
    return this.userRepository.findByFirstName(firstName);
  }

  public Optional<User> getUserByLastName(String lastName) {
    return this.userRepository.findByLastName(lastName);
  }

  public Optional<User> getUserByUsername(String userame) {
    return this.userRepository.findByUsername(userame);
  }

  public Optional<User> getUserByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  // add to service for username so it is unique if present

  public void deleteUser(UUID userId) throws NoSuchElementException {
    if (userRepository.findById(userId).isPresent()) {
      userRepository.deleteById(userId);
    } else {
      throw new NoSuchElementException();
    }
  }
}