package com.trai.video_api.video;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;


//When a user fetches a video by ID, the VideoController would handle the request

//VideoController.getAllVideos()

@RestController // needed so that it behaves like a controler
@RequestMapping("/api/v1/videos") // have a separate VideoControllerv2.java if releasing a v2
public class VideoController {

  private VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  // post a video
  @PostMapping
  public ResponseEntity<Video> createVideo(@RequestBody Video video) {
    try {
      return new ResponseEntity<Video>(this.videoService.createVideo(video), HttpStatusCode.valueOf(201));
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e);
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lock exception ocurred");
    }

  }

  // Get video
  // This method fetches a video by its id from the database and returns it if it
  // exists
  @GetMapping("/{id}")
  public ResponseEntity<Video> getVideo(@PathVariable UUID videoId) {
    Optional<Video> video = videoService.getVideoById(videoId);

    if (video.isPresent()) {
      return ResponseEntity.ok(video.get()); // 200 OK
    } else {
      return ResponseEntity.notFound().build(); // 404 Not Found
    }
  }

  // fetch video by title
  @GetMapping("/title/{title}")
  public ResponseEntity<Video> getVideo(@PathVariable String title) {
    Optional<Video> video = videoService.getVideoByTitle(title);

    if (video.isPresent()) {
      return ResponseEntity.ok(video.get()); // 200 OK
    } else {
      return ResponseEntity.notFound().build(); // 404 Not Found
    }
  }

  // fetch video by tags
  @GetMapping("/tags/{tags}")
  public ResponseEntity<Video> getVideoByTags(@RequestParam List<String> tags) {
    Optional<Video> video = videoService.getVideoByTags(tags);

    if (video.isPresent()) {
      return ResponseEntity.ok(video.get()); // 200 OK
    } else {
      return ResponseEntity.notFound().build(); // 404 Not Found
    }
  }

  //fetch video with partial title in the search
  @GetMapping("/search/title/{title}")
  public ResponseEntity<Video> searchVideosByTitle(@PathVariable String title) {
    Optional<Video> video = videoService.getVideosByPartialTitle(title);
      
      if (video.isPresent()) {
        return ResponseEntity.ok(video.get());
      } else {
          return ResponseEntity.notFound().build(); // 404 if no videos found
      }
      
     
  }

  //update the video
  @PutMapping("/{id}")
  public ResponseEntity<Video>updateVideo(@PathVariable UUID videoId, @RequestBody Video updatedVideo) {
    Optional<Video> video = videoService.getVideoById(videoId);
      
    if (video.isPresent()) {
      updatedVideo.setId(videoId);
      videoService.updateVideo(updatedVideo);
      return ResponseEntity.ok(updatedVideo);
    } else {
        return ResponseEntity.notFound().build(); // 404 if no videos found
    }
    
  }

  //delete the video
  @DeleteMapping("/{id}")
public ResponseEntity<Video> deleteVideo(@PathVariable UUID videoId) {
    Optional<Video> video = videoService.getVideoById(videoId);
    
    if (video.isPresent()) {
        videoService.deleteVideo(videoId); // Call service to delete the video
        return ResponseEntity.noContent().build(); // Return 204 No Content (successful deletion)
    } else {
        return ResponseEntity.notFound().build(); // Return 404 Not Found
    }
}

}
