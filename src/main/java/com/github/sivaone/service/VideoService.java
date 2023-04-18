package com.github.sivaone.service;


import com.github.sivaone.dao.VideoRepository;
import com.github.sivaone.domain.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Iterable<Video> findAll() {
        Iterable<Video> all = videoRepository.findAll();
        return all;
    }
}
