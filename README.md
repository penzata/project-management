# **RESTfull web service for Project Management**

The Project Management API represents a kanban board to manage work at an organizational level. It consists of projects, 
divided by tasks, both of which can be assigned to an employee(s).

The API is created with Spring Boot using hexagonal architecture style that implements the Domain-Driven-Design. 
The structure of the project is a multi-module with Maven:
* app - contains controllers and repositories;
* model - contains all models and business logic.

The three main models are: Employee, Task and Project.

#### API's entry points:
### Employee controller: 8 endpoints
4 CRUD endpoints, list all employees(with optional listing parameter by project ID),
get top(5) employees by completed tasks in the last month, assign to project and unassign from project.

### Task controller: 7 endpoints
4 CRUD endpoints, list all tasks(with optional listing parameters by project ID or(and) employee ID),
assign employee and unassign from employee.

### Project controller: 5 endpoints
4 CRUD endpoints and list all projects.

If an employee is deleted it is unassigned from all tasks.
If a project is deleted, all tasks from that project are deleted too and the employees are unassigned from it.

The API uses Java record classes (for serialization and deserialization) 
with Jakarta and Jackson annotations for validations.
There are several validations in the business layer: for unique email, for creating tasks 
(cannot create tasks if project doesn't exist) and individual task can be assigned only to one employee.

API uses embedded H2 database and the DB's persistence is done through Spring Data JPA.

API has a Global Exception Handler to handle the custom, controller and business level exceptions.

API uses springdoc OpenAPI specification for its documentation.