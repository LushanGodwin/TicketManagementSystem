# Highway Ticket Management System

This project is a microservice-based backend application for managing highway tickets, vehicle registrations, user management, and payment processing. The system leverages Spring Boot and Spring Cloud technologies to ensure scalability, resilience, and seamless integration of services.

## Table of Contents

- [Business Scenario](#business-scenario)
- [Requirements](#requirements)
- [Architecture](#architecture)
- [Services](#services)
- [Technologies](#technologies)
- [Setup](#setup)
- [Usage](#usage)
- [Testing](#testing)
- [Postman Collection](#postman-collection)
- [License](#license)

## Business Scenario

In today's dynamic transportation landscape, managing highway tickets efficiently is crucial for ensuring smooth traffic flow and compliance with regulations. This system streamlines the handling of ticket issuance, vehicle registration, user management, and payment processing without relying on external payment gateways.

## Requirements

The assignment requires the implementation of several key components:

- **Service Registry and Discovery:** Utilizing Eureka for dynamic service registration and discovery.
- **Configuration Server:** Implementing a Spring Cloud Config Server for centralized configuration management.
- **API Gateway:** Utilizing Spring Cloud Gateway for efficient routing of client requests to appropriate microservices.
- **Microservices:**
    - Ticket Service: Manages the lifecycle of tickets.
    - Vehicle Service: Handles vehicle operations.
    - User Service: Manages user and owner information.
    - Payment Service: Facilitates secure payment processing internally.

## Architecture

The application architecture consists of several microservices:

- **Service Registry and Discovery (Eureka):**
    - Purpose: Enables microservices to register themselves and discover other services dynamically.
    - Implementation: Each microservice registers with Eureka upon startup, facilitating communication without hardcoded dependencies.
- **Configuration Server (Spring Cloud Config):**
    - Purpose: Centralize configuration management.
    - Implementation: Microservices fetch configurations from the Config Server, allowing updates without redeployment.
- **API Gateway (Spring Cloud Gateway):**
    - Purpose: Serves as a single-entry point for client requests.
    - Implementation: Routes requests to appropriate microservices based on URI and HTTP method, leveraging Eureka for dynamic service discovery.

## Services

1. **Ticket Service:**
    - Business Logic: Manages the lifecycle of tickets.
    - Functions: Creation, status updates, and retrieval of ticket information.

2. **Vehicle Service:**
    - Business Logic: Handles vehicle operations.
    - Functions: Registration, updates, and retrieval of vehicle details.

3. **User Service:**
    - Business Logic: Manages user and owner information.
    - Functions: Registration, profile updates, and credential verification.

4. **Payment Service:**
    - Business Logic: Facilitates secure payment processing internally.
    - Functions: Validate payment details, update ticket statuses upon successful payment confirmation.

## Technologies

- **Spring Boot**
- **Spring Cloud**
- **Eureka**
- **Spring Cloud Config**
- **Spring Cloud Gateway**
- **Postman** (for API testing)

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/highway-ticket-management-system.git
   cd highway-ticket-management-system

## Usage
- **Access the Eureka dashboard at** http://localhost:8761.
- **API Gateway is accessible at** http://localhost:8080.
- Use the API endpoints as per the defined routes in the Postman collection.

