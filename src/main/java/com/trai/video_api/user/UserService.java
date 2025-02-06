package com.trai.video_api.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
 public final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }
}
//add to service for username so it is unique if present
//public void deleteIOU(UUID id) throws NoSuchElementException {
    // if (iouRepository.findById(id).isPresent()) {
    //     iouRepository.deleteById(id);
    //   } else {
    //     throw new NoSuchElementException();
    //   }
    // } 