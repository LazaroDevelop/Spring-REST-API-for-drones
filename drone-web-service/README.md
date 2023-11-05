# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '1.8' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

#Spring-REST-API-for-drones

### My Recommendations

- Use Java 8
- Use Maven to run Spring Boot applications
- Remember: Spring Boot 2+ works with Java 8+


### Reference Documentation
Tecnologies
* Spring
* JSON
## Dependencies
* Spring Boot - 2.6.8
* Spring Data Jpa 
* H2 SQL Embedded
* Lombok
* JUnit4
* Spring Validator
### Running backend app

To run the backend application I recommend using the Maven(CLI) to start the Spring Tomcat server:
* Execute the command ``` mvn dependency:resolve ``` to search application dependencies.
* Execute the command ``` mvn clean install ``` to generate the resources, run tests and build the application.
* Execute the command ``` mvn test ``` to run only the unitary tests.
* Execute the command ``` mvn spring-boot:run ``` to set up the application and then you can consume the REST API in the default port 8080.

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

