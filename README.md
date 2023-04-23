# Phone Warehouse

## Description

A classic SpringBoot REST application to simulate a warehouse of phones with a H2 in-mem database.
The database is

### App Components

1. A REST API controller exposing next 3 endpoints

- [list of phones with their availability](http://localhost:8080/phones/availability) as a GET http operation
- [book a device](http://localhost:8080/warehouse/phones/return/${phoneId}) as a POST http operation
- [complete a booking (return a phone)](http://localhost:8080/warehouse/phones/return/${bookingId}) as a POST http operation

2. A service layer `WarehouseDirectory`
3. 2 repositories for JDBC database operations. `PhoneRepository` and `BookingRepository`
4. 2 domain models
5. Database layer: H2 in mem. The initialization scripts are stored in `schema.sql` and `data.sql` and are called at each app (re)start as long as the H2 is used as an `in-memory` database.
6. 3 Intellij run configuration are provided.
7. Test components are configured to support unit tests as well as integration tests. Just for demo purposes, for `PhoneController`, were provided a unit tests + an integration test.

### Getting Started

Maven is used to build the artifacts.
To test the app invoke in a terminal:
`mvn clean test`

To run the app in a terminal:
`mvn clean spring-boot:run`

To run/test the app in IntelliJ IDEA:

- IntelliJ should be pickup the next shared run configurations

    - `app :: run` - in memory H2 db
    - `app :: run :: dev` - file H2 db (stored in `_local_/data`)
    - `app :: test :: all` - to run all tests directly in IDE
    - `test :: app` - to run all test with maven in IDE

To run http operations against the app a scratch file was shared in `_local_/warehouse.http` file. Change params accordingly in order to use the API
