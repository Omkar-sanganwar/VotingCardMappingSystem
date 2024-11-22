# Voting System Mapping Project

This project demonstrates the use of **Hibernate ORM** for managing entities like `Person`, `VotingCard`, and `Address`. It uses **Many-to-Many** and **One-to-One** relationships, as well as foreign key constraints, to map the entities to a MySQL database.

## Project Description

This application simulates a **Voting System** where:

- A **Person** can have one **VotingCard**.
- A **Person** can have multiple **Addresses**.
- An **Address** can be associated with multiple **Persons**.

### Features Implemented

1. **Many-to-Many Relationship**: Between `Person` and `Address`.
2. **One-to-One Relationship**: Between `Person` and `VotingCard`.
3. **Duplicate Voter ID Check**: Ensures that no two persons have the same voter ID.
4. **Transaction Management**: Implements rollback in case of errors.

## Technologies Used

- **Java**: Programming language
- **Hibernate ORM**: For object-relational mapping
- **MySQL**: Database for storing data
- **Maven**: Build tool for dependency management

## Project Structure

- **com.nov21**: Contains all Java classes (`Person`, `VotingCard`, `Address`, `Main`).
- **hibernate.cfg.xml**: Hibernate configuration file for database connectivity and session management.
- **pom.xml**: Maven configuration file for managing dependencies (Hibernate, MySQL).

## How to Run

1. Clone the repository.
2. Install [MySQL](https://www.mysql.com/).
3. Create a new database **VotingSystemMapping** (or use the existing one).
4. Configure the `hibernate.cfg.xml` file with your MySQL credentials.
5. Use Maven to build the project:
   ```bash
   mvn clean install
