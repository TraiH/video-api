package com.trai.video_api.video;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

//VideoService.getAllVideos()
//Processes the business logic and delegates data retrieval to the repository.
@Service
public class VideoService {

  public final VideoRepository videoRepository;

  public VideoService(VideoRepository videoRepository) {
    this.videoRepository = videoRepository;
  }

  //saves a new video 
  public Video createVideo(Video video) throws IllegalArgumentException, OptimisticLockingFailureException {
    this.videoRepository.save(video);
    return video;
  }
}
