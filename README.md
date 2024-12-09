# Task Manager
 The Task Manager Application is a simple task management tool where users can:
  - Create tasks with a title, description, and status (To Do).
  - View the list of tasks.
  - Edit tasks.
  - Delete tasks.

##	Backend Setup (Quarkus)

  - Clone the repository
  - Import the folder into the Java IDE, Eclipse or other software you have
  - Run the backend, start the Quarkus in developer mode
  - Run the command -> mvn quarkus:dev to start the application
  - The backend server should start at http://localhost:8080
  - Verify API endpoints: Use tools like Postman to check the following endpoints:
    - GET /tasks: Retrieve all tasks.
    - POST /tasks: Add a new task.
    - PUT /tasks/{id}: Update a task.
    - DELETE /tasks/{id}: Delete a task.
  - In the application.properties, there should be the CORS enable for configuration already
