# calculate-rewards-api

## Overview
This Spring Boot application provides a RESTful API to calculate reward points for customers based on their purchase transactions over a specified period.

## Business Logic
Customers earn points based on the following rules:
* 2 points for every dollar spent over $100 in each transaction.
* 1 point for every dollar spent between $50 and $100 in each transaction.
* Example: A $120 purchase = 2 * $20 + 1 * $50 = 90 points.

## Technologies Used
* Java 17
* Spring Boot 3.x
* JUnit 5 for Unit/Integration Testing
* Maven for build management

## Project Structure
* `model`: Contains data structures (Transaction, RewardSummary).
* `service`: Contains the core business logic for calculating points (`RewardService`).
* `controller`: Exposes the REST endpoint (`RewardController`).
* `exception`: Contains global exception handling logic.

## How to Run
1. Clone the repository.
2. Navigate to the project root directory.
3. Run `mvn spring-boot:run` to start the application.
4. The application will run on `http://localhost:8080`.

## API Endpoints
* **GET** `/api/rewards/customers/{customerId}`
  * Fetches the monthly and total reward points for a specific customer.
  * Example test URL: `http://localhost:8080/api/rewards/customers/101`

## Testing
To run the test suite (which includes positive, negative, and exception scenarios), execute:
`mvn test`
