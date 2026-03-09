# ShareIt API

Backend service for a sharing platform that allows users to lend and borrow items.
Users can add items, request to borrow them for a specific time period, and manage bookings.

The application implements a RESTful API built with Spring Boot, JPA, and PostgreSQL.

# Features

## Users
- Create and manage users
- View user information

## Items
- Add items available for sharing
- Update item details
- Search items by text

## Bookings
- Create booking requests
- Approve or reject bookings
- View current and past bookings

## Requests
- Create requests for items not yet available in the system
- View requests from other users

# Tech Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Lombok
- REST API

Spring Boot is commonly used to build production-ready backend services with minimal configuration and embedded servers.

## Architecture

The project follows a layered architecture:
```
controller
service
repository
model
dto
exception
```
Each layer has its own responsibility:
- Controller — REST endpoints
- Service — business logic
- Repository — database access
- DTO — data transfer objects

# Example Endpoints
## Items
``` java
GET /items
```
Get all items for a user.
``` java
POST /items
```
Create a new item.
``` java
GET /items/search?text=drill
```
Search items by text.

## Bookings
``` java
POST /bookings
```
Create a booking request.
``` java
PATCH /bookings/{bookingId}?approved=true
```
Approve or reject booking.

# Database

The application uses PostgreSQL with entities such as:
- Users
- Items
- Bookings
- Item Requests
Relationships are managed using JPA / Hibernate.

# How to Run

Clone the repository
``` bash
git clone https://github.com/oxelen/java-shareit.git
```
Run the application
``` bash
./mvnw spring-boot:run
```
Server will start at
```
http://localhost:8080
```

# What I Practiced in This Project
- Building RESTful APIs
- Implementing business logic with service layer
- Working with relational databases using JPA
- Handling booking logic and request validation
- Designing layered backend architecture
