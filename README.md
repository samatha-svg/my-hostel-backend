# Hostel Maintenance System - Backend 🚀

Full-stack Spring Boot REST API for managing hostel maintenance requests, issue reporting, technician assignment, and task lifecycle management.

---

## 🛠️ Tech Stack
- **Framework**: Spring Boot 3.3.4 (Java 21)
- **Database**: MySQL 8.0
- **ORM / Persistence**: Spring Data JPA / Hibernate
- **Build Tool**: Apache Maven

---

## 📋 Compulsory CRUD REST APIs

| Operation | Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **CREATE** | `POST` | `/api/complaints` | Submit a new maintenance complaint |
| **READ** | `GET` | `/api/complaints` | Fetch all complaints |
| **READ** | `GET` | `/api/complaints/{id}` | Fetch complaint by ID |
| **READ** | `GET` | `/api/complaints/student/{rollNo}` | Fetch complaints for a specific student roll number |
| **UPDATE** | `PUT` | `/api/complaints/{id}` | Update complaint details |
| **UPDATE** | `PATCH` | `/api/complaints/{id}/status` | Update status (Pending ➔ In Progress ➔ Resolved) and work remarks |
| **ASSIGN** | `PATCH` | `/api/complaints/{id}/assign` | Assign technician/maintenance staff |
| **DELETE** | `DELETE` | `/api/complaints/{id}` | Delete a complaint record |
| **STATS** | `GET` | `/api/complaints/stats` | Dashboard summary counts (total, pending, inProgress, resolved) |

---

## ⚙️ How to Run Locally

### 1. Database Configuration
Ensure MySQL is running and database exists:
```sql
CREATE DATABASE hostel_maintenance_db;
```
Credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hostel_maintenance_db
spring.datasource.username=root
spring.datasource.password=root
```

### 2. Run Application
```bash
mvn clean compile
mvn spring-boot:run
```
API runs at: `http://localhost:8080/api/complaints`
