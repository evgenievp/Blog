Blog API

REST API for managing posts and likes with authentication.

Technologies:
- Java 21
- Spring Boot
- Spring Security
- JWT (access + refresh tokens)
- PostgreSQL
- JPA / Hibernate

Features:
- User registration and login
- JWT authentication (stateless)
- Create posts
- Like / unlike posts (toggle logic)
- Each user can like a post only once (DB constraint)
- Get like count per post
- Get users who liked a post

Security:
- Passwords are hashed using BCrypt
- Access token is returned in response body
- Refresh token is stored in HttpOnly cookie
- Authentication is handled via JWT filter

API Endpoints:

POST
   /api/users/register
   /api/users/login
   /api/posts
   /api/likes/like/{postId}

GET
    /api/likes/posts/{postId}/count
    /api/likes/posts/{postId}/users

How to run:
- Clone the repository
- Configure database in application.properties
- Run the application
- Test endpoints using Postman
