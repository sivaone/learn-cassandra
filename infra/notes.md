# About cassandra
* distributed no-sql database
* wide column store
* no single point of failure
* clusters spanning multiple datacenters
* asynchronous master-less replication
* AP system with Tunable consistency 
* Uses Cassandra Query Language (CQL)

# Configuration
* Default configuration file is cassandra.yaml store in /etc/cassandra directory


# References
* [Strengths and weaknesses](https://alronz.github.io/Factors-Influencing-NoSQL-Adoption/site/Cassandra/Results/Strengths%20and%20Weaknesses/)

# CQLSH

```
    CREATE KEYSPACE excalibur
    WITH replication = {'class': 'NetworkTopologyStrategy', 'replication_factor' : 3};
    
    DESCRIBE KEYSPACE excalibur;
    
    CREATE TYPE address_type (
        street text,
        city text,
        state text,
        zipcode text
    )
    
    CREATE TABLE customer_by_email (
        first_name text,
        last_name text,
        address frozen<address_type>
        email text,
        PRIMARY KEY((email), first_name, last_name)
    );
```

## INSERT
```
    
```

## Batch INSERT
```
BEGIN BATCH

INSERT INTO customer_by_email ... ;
UPDATE customer_by_email ... ;

APPLY BATCH;

```