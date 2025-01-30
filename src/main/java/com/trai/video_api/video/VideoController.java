package com.trai.video_api.video;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//When a user fetches a video by ID, the VideoController would handle the request
//@‌RestController //needed so that it behaves like a controler
//@‌RequestMapping("api/v1/videos") endpoints need to have an ID one for each video
//do we need an endpoint for each user?

//@GetMapping("/{id}")
// public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
//     return ResponseEntity.ok(videoService.getVideoById(id));
// }
//VideoController.getAllVideos()

public class VideoController {

    @GetMapping("/{id}")
    public  getIou(@PathVariable UUID id){

    }

}
