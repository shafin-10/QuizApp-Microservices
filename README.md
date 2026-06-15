# Microservices Quiz Application

A Spring Boot Microservices-based Quiz Application built using Spring Boot, Spring Cloud (Eureka, API Gateway, OpenFeign), and PostgreSQL.

This project demonstrates microservices communication, service discovery, and API gateway routing.

---

# Architecture

This system consists of:

1. Service Registry (Eureka Server) - Port: 8761  
2. API Gateway - Port: 8765  
3. Question Service - Port: 8081  
4. Quiz Service - Port: 8080  

---

# Technologies Used

- Java
- Spring Boot
- Spring Cloud (Eureka, Gateway, OpenFeign)
- Spring Data JPA
- PostgreSQL
- Lombok

---

# Database Setup

Create two PostgreSQL databases:

```sql
CREATE DATABASE questionDb;
CREATE DATABASE quizDb;
