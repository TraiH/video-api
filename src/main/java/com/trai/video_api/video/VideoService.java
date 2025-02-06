package com.trai.video_api.video;

import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

//VideoService.getAllVideos()
//Processes the business logic and delegates data retrieval to the repository.
@Service
public class VideoService {

    public final VideoRepository videoRepository;

    // initialise the repository dependency (dependency injection) so that the
    // service layer can communicate with the database
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // saves a new video to db
    public Video createVideo(Video video) throws IllegalArgumentException, OptimisticLockingFailureException {
        this.videoRepository.save(video);
        return video;
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

    // update the video
    public void updateVideo(Video updateVideo) {
        videoRepository.save(updateVideo); // Use save() to update the video
    }

    //delete the video
    public void deleteVideo(UUID videoId) {
        videoRepository.deleteById(videoId); // Delete the video by its ID using the repository
    }
}
