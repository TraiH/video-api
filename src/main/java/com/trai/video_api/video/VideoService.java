package com.trai.video_api.video;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.trai.video_api.user.User;
import com.trai.video_api.user.UserRepository;

//VideoService.getAllVideos()
//Processes the business logic and delegates data retrieval to the repository.
@Service
public class VideoService {

    public final VideoRepository videoRepository;
    public final UserRepository userRepository;

    // initialise the repository dependency (dependency injection) so that the
    // service layer can communicate with the database
    public VideoService(VideoRepository videoRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    // saves a new video to db
    public Video createVideo(Video video, UUID userId) throws IllegalArgumentException, OptimisticLockingFailureException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            video.setUser(user.get());
            return videoRepository.save(video);
        } else {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
        
        
        // try {
        //     User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        //     video.setUser(user);
        //     return videoRepository.save(video);
        // } catch (IllegalArgumentException e) {
        //     throw new IllegalArgumentException("Invalid video data: " + e.getMessage(), e);
        // } catch (DataAccessException e) {
        //     throw new RuntimeException("Database error occurred while saving the video.", e);
        // }
    }

  



    // // saves a new video to db
    // public Video createVideo(Video video) throws IllegalArgumentException, OptimisticLockingFailureException {
    //     try {
    //         return videoRepository.save(video);
    //     } catch (IllegalArgumentException e) {
    //         throw new IllegalArgumentException("Invalid video data: " + e.getMessage(), e);
    //     } catch (DataAccessException e) {
    //         throw new RuntimeException("Database error occurred while saving the video.", e);
    //     }
    // }
    // get all videos
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    // fetch video from db
    public Optional<Video> getVideoById(UUID videoId) {
        return videoRepository.findById(videoId);
    }

    // fetch video by title
    public Optional<Video> getVideoByTitle(String title) {
        return videoRepository.findByTitle(title);
    }

    // fetch video by tags
    public Optional<Video> getVideoByTags(String tags) {
        return videoRepository.findByTags(tags);
    }

    // fetch a video if a partial title is searched
    public Optional<Video> getVideosByPartialTitle(String title) {
        return videoRepository.findByTitleContainingIgnoreCase(title);
    }

    // //Fetch all videos for a specific user by userId
    public List<Video> getAllVideosForUser(UUID userId) {
        // Find all videos where the user ID matches the provided userId
        try {
            return videoRepository.findByUserId(userId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching videos for user: " + userId, e);
        }

    }

    // update the video
    public Video updateVideo(Video updatedVideo) {
        if (videoRepository.existsById(updatedVideo.getId())) {
            try {
                return videoRepository.save(updatedVideo);
            } catch (OptimisticLockingFailureException e) {
                throw new OptimisticLockingFailureException("Conflict while updating the video.", e);
            } catch (DataAccessException e) {
                throw new RuntimeException("Database error while updating the video.", e);
            }
        } else {
            throw new NoSuchElementException("Video not found with ID: " + updatedVideo.getId());
        }
    }

    // Delete video by ID
    public void deleteVideo(UUID videoId) {
        if (videoRepository.existsById(videoId)) {
            try {
                videoRepository.deleteById(videoId);
            } catch (DataAccessException e) {
                throw new RuntimeException("Error occurred while deleting video ID: " + videoId, e);
            }
        } else {
            throw new NoSuchElementException("Video not found with ID: " + videoId);
        }
    }
}
