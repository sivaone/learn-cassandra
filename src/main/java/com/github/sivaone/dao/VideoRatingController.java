package com.github.sivaone.dao;


import com.github.sivaone.domain.VideoRating;
import com.github.sivaone.service.VideoRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
