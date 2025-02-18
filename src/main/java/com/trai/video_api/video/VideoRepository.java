package com.trai.video_api.video;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

//Purpose: The Repository layer interacts directly with the database. It contains methods for performing CRUD operations on data.
//VideoRepository.findAll()
@Repository
public interface VideoRepository extends ListCrudRepository<Video, UUID>{

    // querie methods use optional rather than list because I want a unique result

    Optional<Video> findByTitle(String title); //returns video by title

    Optional<Video> findByTags(String tags); //returns user by tags

    Optional<Video> findByTitleContainingIgnoreCase(String title); // finds partial title search

    Optional<Video> findByUserId(UUID userId);

}
