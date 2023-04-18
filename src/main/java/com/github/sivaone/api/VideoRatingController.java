package com.github.sivaone.api;


import com.github.sivaone.domain.VideoRating;
import com.github.sivaone.service.VideoRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/rating")
public class VideoRatingController {

    private final VideoRatingService ratingService;

    public VideoRatingController(VideoRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoRating> getVideoRatingById(@PathVariable String id) {
        Optional<VideoRating> videoRating = ratingService.findVideoRatingById(id);
        return ResponseEntity.of(videoRating);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveOrUpdate(@PathVariable String id, @RequestBody Map<String, Long> request) {
        ratingService.saveVideoRating(id, request.get("rating"));
    }
}
