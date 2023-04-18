package com.github.sivaone.api;


import com.github.sivaone.domain.Video;
import com.github.sivaone.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Video>> findAll() {
        Iterable<Video> all = videoService.findAll();
        return ResponseEntity.ok(all);
    }
}
