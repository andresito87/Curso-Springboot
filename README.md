# Springboot Course

This is a Spring Boot course project that demonstrates the use of Spring Boot framework for building web applications

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher
- An IDE such as IntelliJ IDEA or Eclipse
- Postman for testing APIs
- MySQL or any other relational database

### Getting Started

1. Clone the repository to your local machine using the following command:

   ```bash
   git clone
   ````

2. Navigate to the project directory:

   ```bash
    cd portfolio
    ````

3. Open the project in your preferred IDE.
4. Configure the database connection in the `application.properties` file located in the `src/main/resources` directory. Update the following properties with your database credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

## Running the Application

1. Build the project using Maven:

   ```bash
   mvn clean install
   ```

2. Run the application using the following command:

   ```bash
    mvn spring-boot:run
    ```

## Projects Images


## Udemy Course Link

[Java: Spring Boot - Guía definitiva](https://www.udemy.com/course/java-spring-boot-guia-definitiva/)