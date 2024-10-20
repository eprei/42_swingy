# Swingy

## Text-based RPG with GUI

This project is part of the Java curriculum at 42, focusing on GUI programming and software design patterns.

### Project Overview

Develop a text-based RPG (Role Playing Game) with both console and GUI interfaces, following the Model-View-Controller (MVC) architecture.

### Features

- Multiple hero types with different stats
- Turn-based gameplay on a square map
- Villains with varying power levels
- Combat system
- Experience and leveling system
- Artifacts (Weapon, Armor, Helm)
- State preservation between game sessions

### Requirements

- Java (compliant with latest LTS version)
- Maven for build automation
- Swing framework for GUI
- Annotation-based user input validation

### Building the Project

Use Maven to build the project:

```
mvn clean package
```

This will generate a runnable .jar file.

### Running the Game

Console mode:
```
java -jar swingy.jar console
```

GUI mode:
```
java -jar swingy.jar gui
```

### Gameplay

- Create a hero or select an existing one
- Navigate the map, battle villains, and collect artifacts
- Reach the map border to win the game

### Note

This project is designed to teach GUI programming, MVC architecture, and basic game development concepts in Java. It's not intended as a full-fledged commercial game.
