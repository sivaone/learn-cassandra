package com.github.sivaone.config;


import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

import java.net.InetSocketAddress;

// Cassandra datastax driver CqlSession config
//@Configuration
public class CassandraConfig {

    public @Bean CqlSession cqlSession() {
        return CqlSession.builder()
                .withKeyspace("killrvideo")
                .withAuthCredentials("cassandra", "cassandra")
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("datacenter1")
                .build();
    }

    public @Bean CqlTemplate cqlTemplate(CqlSession cqlSession) {
        return new CqlTemplate(cqlSession);
    }
}
