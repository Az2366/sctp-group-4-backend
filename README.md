# **E-Commerce Backend**

![License](https://img.shields.io/badge/license-MIT-green)  
![Version](https://img.shields.io/badge/version-1.0.0-blue)  

## **Table of Contents**

- [Description](#description)
- [Features](#features)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)
- [Testing](#testing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

---

## **Description**

This project is a backend service designed to manage e-commerce operations, including customer data, orders, and inventory. Built with Spring Boot and with Maven as the build tool, it provides a RESTful API to interact with the database and manage resources efficiently.

---

## **Features**

- User registration and authentication
- CRUD operations for customers, items, and orders
- Support for multiple membership types
- Real-time updates with H2 in-memory database
- Well-documented REST API

---

## **Getting Started**

These instructions will guide you on setting up the project on your local machine for development and testing purposes.

---

## **Installation**

### Prerequisites

Before installing the project, ensure you have the following:

- [Java 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/)
- An IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/)

### Steps

1. **Clone the Repository**  
   Open your terminal and run:
   ```bash
   git clone https://github.com/<your-username>/<your-repo>.git
   cd <your-repo>
2. **Build the Project
   Run the following command to build the project and resolve dependencies:
   ```bash
   mvn clean install
3. **Build the Project
   Use Maven to start the application:
   ```bash
   mvn spring-boot:run

---

## **Configuration**

### Database
To access the h2 console, navigate to:
   ```bash
   http://localhost:8080/h2
   ```

---

## **Usage**

1. **Run DataLoader**  
   The application preloads data (customers and items) using the DataLoader component.
2. **Access API
   Use tools like Postman or cURL to interact with the API. See the API Endpoints section
   for details.

---

## **API Endpoints**

1. **Refer to Swagger UI for API Documentation**
   ```bash
   http://localhost:8080/swagger-ui/index.html
   ```

---

## **Technologies Used**

- **Framework**: Spring Boot
- **Database**: H2 Database (In-Memory)
- **Language**: Java 17
- **Build Tool**: Maven
- **Testing**: JUnit, Mockito
- **Others**: Lombok, Hibernate

---

## **Testing**

### Running Tests

1. **Run Unit Tests**  
   Execute the following command to run tests:
   ```bash
   mvn test
2. **Generate Test Reports**  
   Maven generates test reports in the target/surefire-reports directory.

---

## **License**

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## **Acknowledgements**

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)  
- [H2 Database Documentation](https://www.h2database.com/)  
- [Maven Documentation](https://maven.apache.org/)  
- Special thanks to all contributors and developers who made this project possible.
