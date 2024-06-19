# Customer Management Application
This Spring Boot application is designed to manage customer information, including full name, email, and phone number. The application automatically tracks the creation and update timestamps for each customer record in milliseconds.

## Features
- **Customer Management**: Create, read, update, and delete customer records.
- **Validation**: Ensure data integrity with validation constraints on customer fields.
- **Timestamps**: Automatically track creation and update timestamps for customer records.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Docker
- PostgreSQL

## Installation 

1. **Install Docker**: Download and install Docker from the [official website](https://www.docker.com/products/docker-desktop).

2. **Clone the Repository**:
   ```sh
   git clone https://github.com/tetianamakar/customers-api.git
   cd customers-api

3. **Start the Application**:
      ```sh
      docker-compose up -d

## API Testing Guide

1. **Install Postman**: Ensure Postman is installed on your machine.

2. **Import Collection**: Import the Postman collection in the root directory. Start making API calls.
