# Java RESTful Video API

This project is a **RESTful API** for managing user accounts and allowing them to upload and store their videos. Users can:  
- **Create** `POST` videos and user accounts  
- **Retrieve** `GET` videos and user accounts  
- **Update** `PUT` video details and user information  
- **Delete** `DELETE` videos or user accounts  

Built with **Spring Boot** and **MySQL** this API follows **CRUD** and is designed for video content management.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java 21
- Maven 3.6.3 or later
- MySQL 8.0 or later

## Set-up

### Clone Repository
1. Fork this repository in your GitHub account
2. Clone your fork locally.

```sh
git clone https://github.com/TraiH/video-api
cd video-api
```

### Create MySQL Database
1. Login to MySQL:

```sh
mysql -u root -p
```
> :bulb: **Note:** If your root user doesn't have a password set, omit the `-p` flag.

2. Create a new database:

```sh
CREATE DATABASE IF NOT EXISTS videos;
exit;
```

### Initialise Project

1. Open your repository in VS Code
2. Add the following values to `src/main/resources/application.properties`:

```properties
spring.application.name=video-api
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=true
spring.config.import=optional:./local.properties
```
3. In order to prevent sensitive values from being committed to version control, add a new entry to the `.gitignore` file:

```
local.properties
```

4. Create a new file at `src/main/resources/local.properties` and paste in the following: 

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/videos

# Replace "root" with your database user, if applicable
spring.datasource.username=root

# Specify your database user's password, if applicable. If your database user doesn't have a password set, delete the line below
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

5. Replace the username and password values with your MySQL credentials. **IMPORTANT:** Ensure there are no spaces before or after the password.

## Run Application

To start the API, run the following command in your terminal:

### macOS / Git Bash

```sh
./mvnw spring-boot:run
```

### Windows Command Prompt

```cmd
mvnw spring-boot:run
```

If successful, you should see output that ends similarly to the following:

```
2025-01-30T22:18:08.661Z  INFO 5891 --- [video-api] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-01-30T22:18:08.666Z  INFO 5891 --- [video-api] [  restartedMain] com.trai.VideoApiApplication             : Started VideoApiApplication in 1.953 seconds (process running for 2.146)
```

**IMPORTANT**: If everything is working correctly, the output will appear "stuck" and the command prompt won't return until you stop the application, which should now be running at http://localhost:8080/api/videos

### Stop Application
To stop the API
```
Press `Ctrl + C`
```
### Import MySQL Dump
To set up the database using the provided MySQL dump file, follow these steps:

1. Open Terminal: Open your terminal or command prompt.

2. Navigate to the Project Directory: Change to the directory where the database_dump.sql file is located.
```sh
cd /path/to/your/project 
```

3. Import the Dump File: Use the `mysql` command to import the dump file into your MySQL database. Replace `YOUR_DATABASE_NAME`, `YOUR_USERNAME`, and `YOUR_PASSWORD` with your actual database name, MySQL username, and password.

For example, if your database name is `videos`, your username is `root`, and your password is `password123`, the command would be:

Enter Password: Enter your MySQL password when prompted.

## Project Structure
```
video-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── trai/
│   │   │           └── video_api/
│   │   │               ├── user/
│   │   │               ├── video/
│   │   │               └── config/
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               └── trai/
│                   └── video_api/
├── .gitignore
├── README.md
├── pom.xml
└── database_dump.sql
```
## Running Tests
To run the tests for this project, use the following command:
```sh
./mvnw clean test
```

## API Endpoints
The API endpints are specified in the UserController.java and VideoController.java files. The endpoints can be used in the Postman application.

### User Endpoints
| Method  | Endpoint                   | Description             |
|---------|----------------------------|-------------------------|
| `POST`  | /api/v1/users              | Create a new user       |
| `GET`   | /api/v1/users              | Retrieve all users      |
| `GET`   | /api/v1/users/{userId}     | Retrieve user details   |
| `GET`   | /api/v1/users/first-name/{firstName} | Retrieve user by first name |
| `GET`   | /api/v1/users/last-name/{lastName}   | Retrieve user by last name  |
| `GET`   | /api/v1/users/username/{username}    | Retrieve user by username   |
| `GET`   | /api/v1/users/email/{email}          | Retrieve user by email      |
| `PUT`   | /api/v1/users/{userId}     | Update user information |
| `DELETE`| /api/v1/users/{userId}     | Delete a user account   |

### Video Endpoints
| Method  | Endpoint                   | Description              |
|---------|----------------------------|--------------------------|
| `POST`  | /api/v1/user/{userId}/videos | Upload a new video       |
| `GET`   | /api/v1/videos             | Get all videos           |
| `GET`   | /api/v1/videos/{videoId}   | Get a specific video     |
| `GET`   | /api/v1/title/{title}      | Get video by title       |
| `GET`   | /api/v1/tags/{tags}        | Get video by tags        |
| `GET`   | /api/v1/search/title/{title} | Search videos by title   |
| `GET`   | /api/v1/users/{userId}/videos | Get all videos for a specific user |
| `PUT`   | /api/v1/videos/{videoId}   | Update video metadata    |
| `DELETE`| /api/v1/videos/{videoId}   | Delete a video           |