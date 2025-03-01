package com.trai.video_api.video;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;

//When a user fetches a video by ID, the VideoController would handle the request

@RestController // needed so that it behaves like a controler
@RequestMapping("/api/v1") // have a separate VideoControllerv2.java if releasing a v2
public class VideoController {

  private VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  // get all videos
  @GetMapping("/videos")
  public ResponseEntity<List<Video>> getAllVideos() {
    List<Video> video = videoService.getAllVideos();
    return ResponseEntity.ok(video); 

  }

  // post a video
  @PostMapping("/user/{userId}/videos")
  public ResponseEntity<Video> createVideo(@RequestBody Video video, @PathVariable UUID userId) {
    try {
      return new ResponseEntity<Video>(videoService.createVideo(video, userId), HttpStatusCode.valueOf(201));
      //return ResponseEntity.ok(userId);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Invalid data:  Please check the video details and try again.", e);
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "The video data was modified by another process. Please try again.");
    }

  }
  // Get video
  // This method fetches a video by its id from the database and returns it if it
  // exists
  @GetMapping("/{videoId}")
  public ResponseEntity<Video> getVideo(@PathVariable UUID videoId) {
    try {
      Optional<Video> video = videoService.getVideoById(videoId);

      if (video.isPresent()) {
        return ResponseEntity.ok(video.get()); 
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Invalid data:  Please check the video details and try again.", e);
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Update conflict occurred", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
    }
  }

  // fetch video by title
  @GetMapping("/title/{title}")
  public ResponseEntity<Video> getVideo(@PathVariable String title) {
    Optional<Video> video = videoService.getVideoByTitle(title);

    if (video.isPresent()) {
      return ResponseEntity.ok(video.get()); 
    } else {
      return ResponseEntity.notFound().build(); 
    }
  }

  // fetch video by tags
  @GetMapping("/tags/{tags}")
  public ResponseEntity<Video> getVideoByTags(@PathVariable String tags) {
    Optional<Video> video = videoService.getVideoByTags(tags);

    if (video.isPresent()) {
      return ResponseEntity.ok(video.get()); 
    } else {
      return ResponseEntity.notFound().build(); 
    }
  }

  // fetch video with partial title in the search
  @GetMapping("/search/title/{title}")
  public ResponseEntity<Video> searchVideosByTitle(@PathVariable String title) {
    Optional<Video> video = videoService.getVideosByPartialTitle(title);

    if (video.isPresent()) {
      return ResponseEntity.ok(video.get());
    } else {
      return ResponseEntity.notFound().build(); 
    }

  }

  // //find all videos by specific user
  @GetMapping("/{userId}/videos")
  public ResponseEntity<List<Video>> getAllVideosForUser(@PathVariable UUID userId) {
    List<Video> video = videoService.getAllVideosForUser(userId);
    if (video.isEmpty()) {
      return ResponseEntity.notFound().build(); 
    }
    return ResponseEntity.ok(video); 
  }  

  // update the video
  @PutMapping("/{videoId}")
  public ResponseEntity<Video> updateVideo(@PathVariable UUID videoId, @RequestBody Video updatedVideo) {
    Optional<Video> video = videoService.getVideoById(videoId);

    if (video.isPresent()) {
      updatedVideo.setId(videoId);
      videoService.updateVideo(updatedVideo);
      return ResponseEntity.ok(updatedVideo);
    } else {
      return ResponseEntity.notFound().build(); 
    }

  }

  // delete the video
  @DeleteMapping("/{videoId}")
  public ResponseEntity<Video> deleteVideo(@PathVariable UUID videoId) {
    try {
      Optional<Video> video = videoService.getVideoById(videoId);

      if (video.isPresent()) {
        videoService.deleteVideo(videoId); // Call service to delete the video
        return ResponseEntity.noContent().build(); // Return 204 No Content (successful deletion)
      } else {
        return ResponseEntity.notFound().build(); // Return 404 Not Found
      }
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting video", e);
    }
  }
}
