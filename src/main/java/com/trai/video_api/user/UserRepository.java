package com.trai.video_api.user;
// store and retrieve users from a database
//name, username, ID, email address

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, UUID>{

    // querie methods use optional rather than list because I want a unique result

    Optional<User> findByUsername(String username); //returns user by username

    Optional<User> findByEmail(String email); //returns user by email address

    Optional<User> findByFirstName(String firstName); 

    Optional<User> findByLastName(String lastName); 

}
