package com.github.sivaone.domain;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.springframework.data.cassandra.core.mapping.CassandraType.*;

@Table("videos")
@Data
public class Video {

    @PrimaryKey
    private UUID videoid;
    private UUID userid;
    private String name;
    private String description;
    private String location;

    @Column("location_type")
    private Integer locationType;

    @Column("preview_thumbnails")
    @CassandraType(type = Name.MAP, typeArguments = {Name.TEXT, Name.TEXT})
    private Map<String, String> previewThumbnails;
    private Set<String> tags;
    private Set<@Frozen VideoMetadata> metadata;

    @Column("added_date")
    private LocalDateTime addedDate;

}
