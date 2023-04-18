package com.github.sivaone.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Set;

@UserDefinedType("video_metadata")
@Data
public class VideoMetadata {

    private Integer height;
    private Integer width;

    @Column("video_bit_rate")
    private Set<String> videoBitRate;
    private String encoding;

}
