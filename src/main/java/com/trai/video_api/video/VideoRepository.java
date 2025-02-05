package com.trai.video_api.video;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

//Purpose: The Repository layer interacts directly with the database. It contains methods for performing CRUD operations on data.
//VideoRepository.findAll()
public class VideoRepository {
public interface VideoRepository extends ListCrudRepository<Video, UUID>{

    // querie methods use optional rather than list because I want a unique result

    Optional<Video> findByUsername(String username); //returns user by username

    Optional<User> findByEmail(String email); //returns user by email address

    Optional<User> findByFirstName(String firstName); 

    Optional<User> findByLastName(String lastName); 

}

}
