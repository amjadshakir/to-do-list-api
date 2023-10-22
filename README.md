# To Do List Application (Task API)

The To-Do List Application is a Spring Boot-based application that simplifies the management of To-Do task list. With
this application, users can effortlessly add, retrieve, update, and delete to-do task through RESTful endpoints that
interact with H2 in mem tasks database.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

The To-Do List Application boasts a variety of features that cater to task management:

- **Add a new Task**: Easily add a new task.

- **Retrieve all Tasks**: Retrieve all tasks for quick access to your
  task list.

- **Retrieve a task by id**: Retrieve details of a task by a specific id

- **Retrieve tasks by title**: Retrieve tasks by title

- **Update an existing task**: Effortlessly update a task.

- **Delete a task by id**: Delete unwanted task by specifying their unique ID.

- **Exception Handling**: Robust exception handling ensures a smooth user experience by handling scenarios like task not found
- or invalid input or exception while interacting with database etc.

There is a health endpoint to check if the application is up and running.

## Prerequisites

Before diving into the To-Do List Application, ensure that you've met these prerequisites:

- **Java Development Kit (JDK)**: Install JDK 17 or a higher version to compile and run the application.

- **Database**: Currently the application is configured to run against an in memory H2 database.
- However, you can choose to persist your data by setting up a PostgreSQL or another compatible database.

## Getting Started

To kickstart your journey with the To-Do List Web Application, follow these simple steps:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/amjadshakir/to-do-list-api.git
   cd to-do-list-api
   ```

2. **Database Configuration**:

    - Locate the `application.properties` file in the `src/main/resources` directory.
    - Provide the necessary database connection details to configure your database if you choose to use to persist your data, else you can omit the database configuration.

3. **Build and Run**:

    - Build the application using Maven:

      ```bash
      mvn clean install spring-boot:run
      ```

    - Run the application by executing `ToDoListApplication` within your IDE or through the command line:

      ```bash
      java -jar target/to-do-list-api-0.0.1.jar
      ```

   The application will now be up and running at `http://localhost:8081`.

## Usage

Once the application is running, you can seamlessly manage your tasks by making requests to the provided API
endpoints.
Feel free to use tools like Postman or `curl` for making API requests and manage your tasks.

## Health Endpoint
- **GET /actuator/health**: check the status of the application.

## API Endpoints

The To-Do List Application offers the following API endpoints for your convenience:

- **POST /api/v1/tasks**: Add a new task.
- **GET /api/v1/tasks**: Retrieve all tasks.
- **GET /api/v1/tasks/{id}**: Retrieve a specific task by id.
- **PUT /api/v1/tasks/{id}**: Update the existing task.
- **DELETE /api/v1/tasks/{id}**: Delete a specific task by id.

To manually test the Application I have added the Postman collection in the root directory of the project

File name : - **To Do List API.postman_collection.json}**


## Contributing

We welcome contributions to enhance the To-Do List Application. Here's how you can get involved:

1. **Fork the Project**: Fork the project to your GitHub account.

2. **Create a Feature Branch**: Create a feature branch in your forked repository, e.g., `feature/your-feature`.

3. **Commit Your Changes**: Commit your code changes with a descriptive message, e.g., 'Add some feature'.

4. **Push to Your Branch**: Push your changes to the branch in your forked repository.

5. **Open a Pull Request**: Open a pull request to the main project repository.

## License

This project is licensed under the Apache License. For complete licensing details, refer to the [LICENSE](LICENSE) file.

# Assumptions

# Approaches

# Future thoughts




