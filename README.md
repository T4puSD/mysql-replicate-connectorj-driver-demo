## Demo Spring Boot project With Mysql Connector/j Replication Driver
This is a project to demonstrate the capabilities of mysql connector/j replicate driver and how we can utilize
it in spring boot project.

### When to use it
This system architecture is best suited when we can manage to deploy mysql in a cluster
and single primary database will replicate row level change log into replica read only secondary 
database.

### About Replication Driver
The connector/j `replication` driver helps to keep application layer clean of any 
data source related logic. We as a developer don't have to know how we need to
segregate the read's from writes and route our queries according to the 
correct data source and when to send the query to primary and when to send it in 
secondary read only data source.

### How to deploy mysql in cluster (Localhost | Docker)
Use this github repository to spin up a mysql cluster with one primary and one replication database instance.
`https://github.com/vbabak/docker-mysql-master-slave`

### How to use full cluster of mysql in Spring Boot
We just need to declare the proper mysql url for the replicate driver
in the `application.properties` file

```properties
spring.datasource.url=jdbc:mysql:replication://[primary-db-host]:[primary-db-port],[secondary-db-host]:[secondary-db-port],[other-seconday-hosts-and-ports....]/mydb
```
We can add as many replica read only secondary in our connection string as we want. The driver will auto load balance 
between them.

> **Note:** All the database including primary should have a single user having common credentials