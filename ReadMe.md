# Crust Project

The Crust project Application Backend is a Java 17-based, Spring Boot 3-powered, Spring Security 6-secured, PostgreSQL-backed, and Multi-Module structured solution. It serves as the backbone for a contemporary task management system, facilitating seamless task creation, organization, and monitoring. By implementing OAuth2, it ensures secure user authentication and authorization. Its modular design streamlines development and maintenance.
## Key Features

- Java 17: This backend harnesses the latest Java 17 capabilities, offering superior performance, enhanced memory management, and heightened developer productivity.

- Spring Boot 3: Spring Boot 3 provides a robust foundation for microservices development, featuring a streamlined development process, an embedded server, auto-configuration, and potent dependency management.

- Spring Security 6: Spring Security 6 ensures comprehensive security measures, encompassing user authentication, authorization, role-based access control, and protection against common security threats.

- PostgreSQL Database: The system relies on PostgreSQL for data storage and management, encompassing user profiles, tasks, notifications, and more, ensuring data integrity and scalability.

- Multi-Module Architecture: The backend employs a modular approach, dividing it into User Management, Task Management, Notification, and Gateway modules. This facilitates code reusability, maintenance, and scalability.

- OAuth2 Integration: The backend seamlessly integrates OAuth2 for secure authentication and authorization. Users can utilize their Google, GitHub, or Facebook accounts for login, enhancing both convenience and security.

- RESTful APIs: The backend offers a suite of RESTful APIs that facilitate communication between client applications and the server. These APIs cover user management, task management, notifications, and more.

## Benefits

- Scalability: The modular architecture simplifies scalability, enabling the addition of new features without disrupting existing components.

- Security: Stringent security is ensured through Spring Security 6 and OAuth2, while PostgreSQL safeguards user data.

- Flexibility: The backend leverages Spring Boot's flexibility and Spring Security's customization capabilities, adapting to diverse application requirements.

Performance: Java 17's performance enhancements, coupled with Spring Boot's optimized configuration, deliver excellent runtime performance.

- Documentation: Thorough API documentation, using tools like Swagger/OpenAPI, facilitates clear communication between frontend and backend developers, simplifying integration.

- The Crustproject Application Backend is a contemporary, secure, and user-centric solution for efficient task management. Leveraging cutting-edge technologies and architectural principles, it empowers businesses and users to manage tasks effectively, boost productivity, and stay organized.

## Technologies Used
- Java (Programming language)
- Springboot (Framework used to develop the APIs)
- Maven (Dependency manager)
- PostgreSQL (Database for data storage)
- JWT (Library for authentication)
- Spring Security (Framework used for security)

## Prerequisites

To build and run this project, you'll need:

- Java JDK 17 or later
- Spring Boot 3.0
- Maven 3.0 or later
- PostgreSQL Database 15 or later

## Features

- User authentication and authorization using Spring Security 
- CRUD operations for managing tasks 
- PostgreSQL database integration 
- API documentation using Swagger 
- OAuth2 integration with github for secure authentication and authorization
- JWT-based token authentication

## Getting Started

To get started with crustproject, you will need to clone this repository to your local machine and set up the necessary dependencies.

### Installation

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/KabirYusuf/crust-project.git
    ```

2. Create PostgreSQL database

   ```bash
   psql> create database crust_db
   ```

3. Configure database username and password
      The database username I used for this project is: crust_admin
      The database password I used for this project is: 12345


4. Server port
      The application runs on port 8080

     ```properties
   # This is optional if you're testing the app in localhost. A demo database is already provided.
   # src/main/resources/application.properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/crust_db
     spring.datasource.username=crust_admin
     spring.datasource.password=12345
     spring.jpa.hibernate.ddl-auto=create-drop(This can be set to either create, create-drop or update)
     ```

5. specify OAuth2 Provider ClientIds and ClientSecrets 
- This is optional if you're testing the app in localhost. A demo clientId and clientSecret is already specified.

5.  Set up the backend server:
   ```bash
     mvn spring-boot:run
   ```

## OAUTH2 Authentication Endpoints

   ```bash
     (github) http://localhost:8080
     
     The above endpoint Authenticates, Authorize, Persit and redirects to a link with a JWT token as as parameter which is used to make subsequent API call 
     
     http://localhost:8080
   ```

## Swagger Authentication Endpoints

   ```bash
     (Swagger) http://localhost:8080/swagger-ui/index.html#
     
     The above Url routes to the swagger UI for testing.
     The Authentication groups relates to Registering with Email and Password
     The Task Management group relates to the Basic CRUD operations for the case study
     http://localhost:8080/?token={token}
   ```

## Default DataBase Credentials

   ```bash
   To use the default database credentials, ensure you are connected to the internet
   username: crust_admin
   database: crust_db
   password: 12345
   port: 5432
   ```

## Functional requirement

- User story: I can register a new account
- User story: I can log in
- User story: I can log in or register with at Github
- User story: I can create a Task
- User Story: I can Update a created Task
- User Story: I can See All Created Task
- User Story: I can Delete Task

## Non-Functional Requirements

The following non-functional requirements must be met:

- Security: The application has robust security measures in place to protect user data and prevent unauthorized access.
- Availability: The application is highly available, with minimal downtime and interruptions in service.
- Performance: The application is optimized for low latency, with fast response times to user requests.