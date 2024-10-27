# Swingy

A text-based RPG game with both console and GUI interfaces, developed as part of the Java curriculum at 42 School. This project focuses on GUI programming and implementing software design patterns, particularly the Model-View-Controller (MVC) architecture.

## Project Overview

Swingy is a minimalistic RPG where players can create and manage multiple heroes of different types, navigating through dynamically sized maps while battling villains and collecting artifacts. The game features:

- Multiple hero types with different stats (Archer, Blacksmith, Warrior, Magician)
- Turn-based gameplay on a square map
- Random villain encounters
- Combat system with experience and leveling
- Artifact system (Weapons, Armor, Helms)
- State persistence between game sessions
- Runtime switchable GUI/Console interfaces

## Technical Implementation

While the project requirements specified basic Java functionality, this implementation includes several enhancements:

- **Spring Boot**: Utilized for dependency injection and application configuration
- **Spring Data JPA**: Implemented for database operations and entity management
- **MySQL Database**: Used for persistent storage (via Docker)
- **Docker**: Containerized database setup for easier deployment
- **Hibernate Validator**: For annotation-based input validation
- **Swing Framework**: For the GUI implementation
- **Maven**: For project build and dependency management

## Prerequisites

- Java 21 or higher
- Maven
- Docker and Docker Compose
- MySQL (provided via Docker)

## Dependencies

Key dependencies used in the project:

```xml
- Spring Boot Starter Data JPA
- MySQL Connector
- Spring Boot Starter Validation
- Lombok
- JetBrains Annotations
- Java GUI Forms RT
```

## Building and Running

### 1. Database Setup

First, start the MySQL database using Docker:

```bash
cd db
docker-compose up -d
```

This will create a MySQL instance with the following configuration:
- Database: swingy
- User: swingyuser
- Password: swingypass
- Port: 3306

### 2. Building the Project

From the project root directory:

```bash
mvn clean package
```

This will generate a runnable JAR file named `swingy.jar`

### 3. Running the Game

The game can be launched in two modes:

Console mode:
```bash
java -jar target/swingy.jar console
```

GUI mode:
```bash
java -jar target/swingy.jar gui
```

## Features

### Hero Management
- Create new heroes with different classes
- View hero statistics
- Equip artifacts
- Level up system

### Game Mechanics
- Dynamic map size based on hero level
- Turn-based movement (North, South, East, West)
- Random villain encounters
- Battle system with attack/defense calculations
- Experience and leveling system
- Artifact drops from defeated villains

### User Interface
- Switchable between console and GUI at runtime
- Intuitive controls
- Visual map representation
- Battle interface
- Hero statistics display

## Design Patterns

The project implements several design patterns:

- **MVC Architecture**: Separates the game logic, data, and presentation
- **Builder Pattern**: For hero creation
- **Repository Pattern**: For data persistence
- **Factory Pattern**: For artifact creation

## ⚠️ Important Security Notice

This is a study project and contains practices that should **NOT** be used in production environments:

- Database credentials and secrets are stored in the repository
- Environment variables are committed to version control
- No security hardening has been implemented

**Best Practices for Production Applications:**
- Never commit sensitive information to version control
- Use environment variables or secure secret management systems
- Store credentials in a secure vault or configuration service
- Use strong, unique passwords
- Implement proper security measures for database access
- Set up proper authentication and authorization

This implementation prioritizes ease of setup for educational purposes over security best practices.
