# user-mgmt-service
A service to manage users in the app.

## Features
- Retrieve, Create, Update, Delete users in the app.

## Technologies Used
- Java 17
- Spring Boot 3.2
- Spring Data JPA
- Spring Security 6.1
- In-mem relational H2 Database

## Setup Instructions
1. Clone the repository from master branch.
2. Before running the app, make sure you have Java 17 installed.
3. Run the app from IDE, make sure Build path is set to JDK 17.
4. Access the application at http://localhost:8080 through the endpoints mentioned below. You can use the postman collection in project directory - it has all the required sample requests. First create a user (using below endpoint itself) and then you can access other secure endpoints too. 

## API Endpoints
- Unsecure Endpoints: These endpoints can be accessed without any authentication
  - `POST /user`: Create new user. Make sure the username is more than 4 letters and doesn't contain any special characters. Make sure the password is more than 6 letters and contains atleast one special character.
- Admin Endpoints: These endpoints can only be accessed by a user of admin role.
  - `GET /user/{userId}`: Get user info for the input user id.
  - `DELETE /user/{userId}`: Delete the user from the system.
  - `PUT /user`: Update details of existing user. Make sure the user id is provided in the input.
  
## Upcoming changes
- Swagger documentation.
- Password validation logic changes.