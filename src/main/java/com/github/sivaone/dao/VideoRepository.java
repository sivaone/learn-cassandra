package com.github.sivaone.dao;

import com.github.sivaone.domain.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VideoRepository extends CrudRepository<Video, UUID> {
}
