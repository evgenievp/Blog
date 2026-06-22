Blog API

Blog API is a Spring Boot REST application for managing users, posts and likes. The project focuses on authentication, authorization, relational database structure and basic social-media-like interactions such as creating posts and liking or unliking them.

The application allows users to register, log in, create posts, like posts, remove their likes, check how many likes a post has and see which users liked a specific post.

Technologies used:

Java 21
Spring Boot
Spring Security
JWT authentication
Spring Data JPA / Hibernate
Microsoft SQL Server
Lombok
Maven

Main features:

User registration and login
JWT-based authentication
Access token generation
Refresh token support
Password hashing with BCrypt
Create posts
Like and unlike posts
Preventing the same user from liking the same post more than once
Getting the number of likes for a post
Getting the users who liked a post
Layered backend structure with controllers, services, repositories, DTOs and entities

Security:

Passwords are stored as BCrypt hashes.
Authentication is handled with JWT tokens.
The access token is returned after successful login.
The refresh token is handled separately.
Protected endpoints require a valid JWT token.
JWT validation is handled through a custom authentication filter.

Database:

The application uses Microsoft SQL Server.
Entities are mapped with JPA / Hibernate.
The main entities are User, Post and Like.
The Like entity represents the relation between a user and a post.

API endpoints:

Authentication:

POST /api/users/register
Registers a new user.

POST /api/users/login
Authenticates an existing user and returns authentication tokens.

Posts:

POST /api/posts
Creates a new post.

Likes:

POST /api/likes/like/{postId}
Likes or unlikes a post. If the user has already liked the post, the like is removed.

GET /api/likes/posts/{postId}/count
Returns the number of likes for a specific post.

GET /api/likes/posts/{postId}/users
Returns the users who liked a specific post.

How to run:

Clone the repository.

Open the project in IntelliJ IDEA or another Java IDE.

Reload Maven dependencies.

Enable annotation processing because the project uses Lombok.

Configure the database connection in application.properties.

Make sure Microsoft SQL Server is running.

Run the Spring Boot application.

Test the endpoints using Postman or another REST client.

Notes:

This project was created as a backend practice project focused on Spring Boot, Spring Security, JWT authentication, database relations and REST API design.

The project does not include a frontend. It is intended to be tested through Postman or another API client.
