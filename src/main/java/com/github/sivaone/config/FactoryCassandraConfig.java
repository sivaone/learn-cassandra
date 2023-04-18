package com.github.sivaone.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;


// TODO: explore further
//@Configuration
public class FactoryCassandraConfig {

    public @Bean CqlSessionFactoryBean cqlSessionFactory() {
        CqlSessionFactoryBean sessionFactory = new CqlSessionFactoryBean();
        sessionFactory.setContactPoints("localhost");
        sessionFactory.setPort(9042);
        sessionFactory.setUsername("cassandra");
        sessionFactory.setPassword("cassandra");
        sessionFactory.setLocalDatacenter("datacenter1");
        sessionFactory.setKeyspaceName("killrvideo");

        return sessionFactory;
    }
}
