package com.github.sivaone.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.config.CassandraTemplateFactoryBean;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;


// Spring cassandra config
@Configuration
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

    public @Bean CassandraMappingContext mappingContext() {
        return new CassandraMappingContext();
    }

    public @Bean CassandraConverter cassandraConverter() {
        return new MappingCassandraConverter(mappingContext());
    }

    public @Bean CassandraTemplateFactoryBean cassandraTemplateFactoryBean(
            SessionFactory sessionFactory,
            CassandraConverter cassandraConverter) {
        CassandraTemplateFactoryBean templateFactory = new CassandraTemplateFactoryBean();
        templateFactory.setSessionFactory(sessionFactory);
        templateFactory.setConverter(cassandraConverter);
        return templateFactory;
    }

    public @Bean CassandraTemplate cassandraTemplate(CassandraTemplateFactoryBean templateFactory) throws Exception {
        return templateFactory.getObject();
    }
}
