# Java RESTful Video API

This project is a **RESTful API** for managing videos and user accounts. Users can:  
- **Create** `POST` videos and user accounts  
- **Retrieve** `GET` videos and user profiles  
- **Update** `PUT/PATCH` video details and user information  
- **Delete** `DELETE` videos or user accounts  

The API allows users to register, log in, and manage their profiles.

Built with **Spring Boot** and **MySQL** this API follows **CRUD** and is designed for video content management.


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
2. Add the following values to src/main/resources/application.properties:

```properties
spring.application.name=video-api
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=true
spring.config.import=optional:./local.properties
```
3. In order to prevent sensitive values from being committed to version control, add a new entry to the .gitignore file:

```
local.properties
```

4. Create a new file at src/main/resources/local.properties and paste in the following: 

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/videos

# Replace "root" with your database user, if applicable
spring.datasource.username=root

# Specify your database user's password, if applicable. If your database user doesn't have a password set, delete the line below
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

5. Replace the username and password values with your MySQL credentials. **IMPORTANT:** Ensure there are no spaces before or after the password.

## Run Application

To start the API, run the following command:

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

## API Endpoints
### User Endpoints
| Method  | Endpoint           | Description             |
|---------|--------------------|-------------------------|
| `POST`  | /api/users      | Create a new user      |
| `GET`   | /api/users/{id} | Retrieve user details  |
| `PUT`   | /api/users/{id}  | Update user information |
| `DELETE`| /api/users/{id}  | Delete a user account  |

### Video Endpoints
| Method  | Endpoint          | Description              |
|---------|------------------|--------------------------|
| `POST`  | /api/videos   | Upload a new video       |
| `GET`   | /api/videos    | Get all videos           |
| `GET`   | /api/videos/{id} | Get a specific video    |
| `PUT`   | /api/videos/{id} | Update video metadata  |
| `DELETE`| /api/videos/{id} | Delete a video         |
