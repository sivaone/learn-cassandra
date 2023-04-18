package com.github.sivaone.service;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.github.sivaone.domain.Video;
import com.github.sivaone.domain.VideoRating;
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

    private final CqlSession cqlSession;

    public VideoRatingService(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
    }

    public Optional<VideoRating> findVideoRatingById(String id) {
        ResultSet rs = cqlSession.execute("SELECT * FROM video_rating where videoid = ?", UUID.fromString(id));
        Row row = rs.one();
        if(Objects.nonNull(row)) {
            UUID videoid = row.get("videoid", UUID.class);
            Long ratingCounter = row.get("rating_counter", Long.class);
            Long ratingTotal = row.get("rating_total", Long.class);

            return Optional.of(new VideoRating(videoid, ratingCounter, ratingTotal));
        } else {
            return Optional.empty();
        }
    }

    // TODO: Incomplete. Need to write api to call this
    public void saveVideoRating(String videoId, Long rating) {
        CassandraOperations template = new CassandraTemplate(cqlSession);
        final UUID id = UUID.fromString(videoId);
        Video video = template.selectOne(Query.query(Criteria.where("videoid").is(id)), Video.class);
        if (Objects.isNull(video)) {
            throw new RuntimeException("Video not found");
        }

        final VideoRating videoRating = template.selectOne(
                Query.query(Criteria.where("videoid").is(id)),
                VideoRating.class
        );
        if (Objects.nonNull(videoRating)) {
            final VideoRating update = new VideoRating(
                    id,
                    Math.addExact(videoRating.getRatingCounter(), 1L),
                    Math.addExact(videoRating.getRatingTotal(), rating)
            );
            template.update(update);

            // another way to update
            /* template.update(
                    Query.query(Criteria.where("videoid").is(id)),
                    Update.empty()
                            .increment("ratingCounter", 1)
                            .increment("ratingTotal", rating),
                    VideoRating.class
            );*/
        } else {
            template.insert(new VideoRating(id, 1L, rating));
        }

    }
}
