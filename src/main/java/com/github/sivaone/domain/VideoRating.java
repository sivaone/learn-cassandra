package com.github.sivaone.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

//public record VideoRating(UUID videoid, Long ratingCounter, Long ratingTotal) {
//}

@Table("video_rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRating {

    @PrimaryKey
    UUID videoid;

    @Column("rating_counter")
    Long ratingCounter;

    @Column("rating_total")
    Long ratingTotal;
}