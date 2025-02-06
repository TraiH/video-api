package com.trai.video_api.user;

import java.util.List;
import java.util.Optional;

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

  
  public Optional<User> getUserByFirstName(String firstName) {
    return this.userRepository.findByFirstName(firstName);
  }

  
  public Optional<User> getUserByLastName(String lastName) {
    return this.userRepository.findByLastName(lastName);
  }

  public Optional<User> getUserByUsername(String userame) {
    return this.userRepository.findByUsername(userame);
  }
}
// add to service for username so it is unique if present
// public void deleteIOU(UUID id) throws NoSuchElementException {
// if (iouRepository.findById(id).isPresent()) {
// iouRepository.deleteById(id);
// } else {
// throw new NoSuchElementException();
// }
// }