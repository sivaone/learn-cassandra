package com.github.sivaone.domain;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("users")
@Data
public class User {

    @PrimaryKey
    private UUID userid;
    private String firstname;
    private String lastname;
    private String email;

    @Column("created_date")
    private LocalDateTime createdDate;
}
