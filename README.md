# 🧩 Grid Game Simulation in Java

A Java-based simulation project that creates and manipulates a 2D grid based on user input. It emphasizes modular design with separate classes for input handling, grid operations, and application logic.

## 📁 Project Structure

```
.
├── Main.java         # Entry point for the application
├── Grid.java         # Core class for grid creation and manipulation
├── InputTaker.java   # Handles user input
```

## 🚀 Features

- Dynamic grid creation
- Cell-based placement/removal operations
- Clean console-based display
- Modular and readable code

## 🎮 How to Run

1. **Compile the project:**

```bash
javac Main.java Grid.java InputTaker.java
```

2. **Run the program:**

```bash
java Main
```

3. **Follow the on-screen instructions to interact with the grid.**

## 🛠️ Requirements

- Java 8 or above
- Terminal or Java IDE (like IntelliJ IDEA, Eclipse, etc.)

## 📚 Class Overview

### `Main.java`

- Controls the overall flow of the program.
- Calls `InputTaker` to get inputs and uses `Grid` for operations.

### `Grid.java`

- Maintains the 2D grid structure.
- Contains methods to initialize, update, and display the grid.

### `InputTaker.java`

- Uses `Scanner` to read input from the user.
- Provides utility methods for common input tasks.

