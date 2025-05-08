# 💰 Loan Management System

**A secure Spring Boot-based Loan Management System that allows customers to apply for loans, admins to approve them, and supports tracking repayments and overdue status**

## 🔧 Setup Instructions

### ✅ Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL (or MySQL)
- IDE (e.g., IntelliJ, VS Code)

### ⚙️ Steps to Run the Project Locally

1. **Clone the repository**:
   git clone https://github.com/DevanshInovation/loan-management-system-App.git
   cd loan-management-system

2. Configure the database:
Update src/main/resources/application.properties:

properties
Copy
Edit
server.port=8082
server.servlet.context-path=/loan-management-api

spring.datasource.url=jdbc:postgresql://localhost:5432/loan_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Run the application:

mvn clean install
mvn spring-boot:run

4. Access Swagger UI:

http://localhost:8081/loan-management-api/swagger-ui/index.html

🗂# Project Modules
i) Customer Management (Register, View, Update)

ii) Loan Application (Apply, Approve, View by Status)

iii) Repayment Handling (Repay, Track Overdue)

iV) Swagger UI for API testing

V) JWT Authentication (secured endpoints)

# Assumptions & Limitations
i) Interest is fixed at 8% annually, calculated using simple interest formula.

ii) Authentication is handled via JWT. Bearer token must be passed in headers.

iii) No frontend included. APIs tested via Swagger UI or Insomnia.

iV) Email notifications and cron-based overdue updates are planned as future improvements.

🗃# Database Schema
**Customer**
Field ||	Type
id	Long
firstName	String
lastName	String
email	String
contactNumber	String
loans  List<Loan>


**Loan**
Field || Type ||	Description
id ||  	Long	
amount || Double || Principal amount
interest ||	Double ||	Calculated interest
durationMonths ||	Integer ||	Duration in months
remainingBalance ||	Double ||	Principal + interest (repayable)
status ||	String ||	PENDING, APPROVED, REPAID, etc
startDate ||	LocalDate ||	Date when loan starts
customer ||	Customer ||	Associated customer

**Repayment**
Field || Type ||	Description
id ||	Long	
loan ||	Loan ||	Associated loan
amount ||	Double ||	Amount repaid
repaymentDate ||	LocalDate ||	Date of repayment


# Authentication
**This application uses JWT Bearer Authentication.**

i) All endpoints require a Bearer Token
ii) Add a @SecurityRequirement(name = "bearerAuth") to secured controllers
iii) Token must be included in the Authorization header:Authorization: Bearer <your-jwt-token>


# Swagger API Documentation
i) OpenAPI 3 support with Swagger UI
ii)Accessible at:http://localhost:8082/loan-management-api/swagger-ui/index.html

#  Git Commit Examples
i) feat: add applyLoan endpoint
ii) fix: correct interest calculation logic
iii)docs: update README with DB schema
iV) chore: add Swagger config for JWT auth


# API Endpoints
 **All endpoints are secured and require a Bearer Token (JWT) in the Authorization header.**

**Customer Endpoints**
POST http://localhost:8081/loan-management-app/customers/register — Register new customer
GET http://localhost:8081/loan-management-app/customers/{id} — Get customer by ID
PUT http://localhost:8081/loan-management-app/customers/{id} — Update customer details

**Loan Endpoints**
POST http://localhost:8081/loan-management-app/loans/apply/{customerId} — Apply for a loan
PUT http://localhost:8081/loan-management-app/loans/approve/{loanId} — Approve a loan (Admin only)
GET http://localhost:8081/loan-management-app/loans/customer/{customerId} — Get all loans for a customer
GET http://localhost:8081/loan-management-app/loans/status/{status} — Get loans by status (PENDING, APPROVED, etc.)
GET http://localhost:8081/loan-management-app/loans/history/{customerId} — Get loan history for a customer
GET http://localhost:8081/loan-management-app/loans/overdue — Get all overdue loans
GET http://localhost:8081/loan-management-app/loans/pending — Get all pending loans

**Repayment Endpoints**
POST http://localhost:8081/loan-management-app/repayments/{loanId} — Make a repayment
GET http://localhost:8081/loan-management-app/repayments/overdue — View overdue repayments

**Authentication** (If Implemented)
POST http://localhost:8081/loan-management-app/auth/register — Register a new user (Admin/Customer)
POST http://localhost:8081/loan-management-app/auth/login — Login and receive JWT token and Token Will be validate 10 hours




    


