# Arch Microservices Platform

**License:** MIT  
**Tech Stack:** Spring Boot, Spring Cloud, React, Docker

## Overview

Arch is a scalable microservices-based platform focused on secure authentication, real-time communication, role-based access control, and asynchronous notifications. The system is designed with production-grade patterns including centralized configuration, service discovery, observability, and containerized deployment.

The platform currently focuses on a **backend-first architecture** with a decoupled frontend client.

## Key Capabilities

- JWT-based authentication and RBAC
- Real-time chat using WebSockets
- Event-driven notification system
- Centralized API Gateway
- Distributed tracing, logging, and metrics
- Dockerized local development

## Repository Structure

```text
.
├── backend/              # All Spring Boot microservices
├── frontend/             # React SPA
├── docker/               # Docker & observability setup
├── documentation/        # Architecture & diagrams
├── Jmeter/               # Load testing
└── README.md

---

## 2️⃣ `BACKEND.md` (Backend-Focused, Resume/GSoC Friendly)

```md
# Backend Architecture – Arch Platform

## Overview

The backend consists of multiple Spring Boot microservices communicating via REST, WebSockets, and asynchronous messaging. Each service is independently deployable and follows clean separation of concerns.

## Services

### auth_server
- User registration and login
- JWT token issuance and validation
- Role-Based Access Control (RBAC)
- Email verification and password reset

### chat_server
- WebSocket-based real-time messaging
- Private and group chat support
- Gateway-routed WebSocket connections

### admin_server
- Administrative APIs
- CRUD operations for users, roles, and permissions

### notification_server
- Asynchronous email notifications
- RabbitMQ event consumers
- Thymeleaf-based email templates

### gateway_server
- Central API entry point
- Request routing and filtering
- Rate limiting and circuit breakers
- Security enforcement

### eureka_server
- Service discovery and registration

### config_server
- Centralized external configuration
- Environment-based profiles

## Data Layer

- PostgreSQL with Flyway migrations
- Redis for caching and session management
- RabbitMQ for event-driven workflows

## Observability

- Zipkin for distributed tracing
- ELK stack for centralized logging
- Prometheus for metrics
- Grafana for dashboards

## Testing

- Unit and integration tests using JUnit
- Testcontainers for infrastructure testing
- Load testing via JMeter
