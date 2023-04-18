package com.github.sivaone.service;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.github.sivaone.domain.Video;
import com.github.sivaone.domain.VideoRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.cassandra.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoRatingService {

//    private final CqlSession cqlSession;

//    public VideoRatingService(CqlSession cqlSession) {
//        this.cqlSession = cqlSession;
//    }

    @Autowired
    private CassandraOperations cassandraTemplate;

    public Optional<VideoRating> findVideoRatingById(String id) {
        UUID videoUuid = UUID.fromString(id);

        // Using CqlSession class for queries
        /*ResultSet rs = cqlSession.execute("SELECT * FROM video_rating where videoid = ?", videoUuid);
        Row row = rs.one();
        if(Objects.nonNull(row)) {
            UUID videoid = row.get("videoid", UUID.class);
            Long ratingCounter = row.get("rating_counter", Long.class);
            Long ratingTotal = row.get("rating_total", Long.class);

            return Optional.of(new VideoRating(videoid, ratingCounter, ratingTotal));
        } else {
            return Optional.empty();
        }*/

        // Using CassandraTemplate for queries
        VideoRating videoRating = cassandraTemplate.selectOne(
                Query.query(Criteria.where("videoid").is(videoUuid)),
                VideoRating.class);
        return Objects.isNull(videoRating) ? Optional.empty() : Optional.of(videoRating);
    }

    public void saveVideoRating(String videoId, Long rating) {
//        CassandraOperations cassandraTemplate = new CassandraTemplate(cqlSession);
        final UUID id = UUID.fromString(videoId);
        Video video = cassandraTemplate.selectOne(Query.query(Criteria.where("videoid").is(id)), Video.class);
        if (Objects.isNull(video)) {
            throw new RuntimeException("Video not found");
        }

        // Note: Counter columns can only be incremented or decremented (cannot be set)
        cassandraTemplate.update(
                Query.query(Criteria.where("videoid").is(id)),
                Update.empty()
                        .increment("ratingCounter", 1)
                        .increment("ratingTotal", rating),
                VideoRating.class
        );
    }
}
