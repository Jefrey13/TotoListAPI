# Java Spring REST API

This is a sample application demonstrating how to build a RESTful API using Java with the Spring framework. The application uses a MySQL database and connects via JDBC and JPA to interact with the data.

## Requirements

- Java Development Kit (JDK) 21
- Maven
- MySQL

3. Configure the `application.properties` file in the `src/main/resources` folder with your MySQL database information:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database?useSSL=false
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

## Project Structure

The project follows the following package structure:

- `com.yourcompany.restapi`: Main package of the application.
  - `controller`: Contains the REST controllers handling HTTP requests.
  - `model`: Defines the classes representing the data models.
  - `repository`: Contains repository interfaces to interact with the database.
  - `service`: (Optional) Can contain additional business logic.

## Available Endpoints

- `GET /api/tasks`: Get all tasks.
- `GET /api/tasks/{id}`: Get a task by its ID.
- `POST /api/tasks`: Create a new task.
- `PUT /api/tasks/{id}`: Update an existing task by its ID.
- `DELETE /api/tasks/{id}`: Delete a task by its ID.
