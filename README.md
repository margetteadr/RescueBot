# RescueBot

## Project Description

RescueBot is a Java-based grid navigation system that simulates an autonomous rescue robot moving through a 2D environment.

The robot must navigate from a starting position to a target location while avoiding obstacles. The system uses Breadth-First Search (BFS) pathfinding to determine whether a path exists and to generate a route to the target.

The project demonstrates Object-Oriented Programming (OOP), pathfinding algorithms, AI-style decision making, file handling, and grid-based simulation.

---

## Features

- 2D grid environment
- Obstacle detection
- Autonomous robot movement
- Breadth-First Search (BFS) pathfinding
- Path reconstruction
- Step tracking
- Score system
- Save game functionality
- Object-Oriented Design

---

## Grid Representation

The environment is represented using a 2D integer array.

| Value | Meaning |
|---------|---------|
| 0 | Empty Cell |
| 1 | Wall / Obstacle |

Example:

```java
int[][] map = {
{0, 0, 0, 0, 0},
{0, 1, 1, 1, 0},
{0, 0, 0, 1, 0},
{0, 1, 0, 0, 0},
{0, 0, 0, 1, 0}
};
```

---

## Classes

### RescueBot
Main controller of the program.

Responsibilities:
- Runs simulation
- Displays grid
- Controls robot movement
- Tracks score and steps

### Robot
Stores robot position and movement methods.

### Grid
Stores map information and grid dimensions.

### PathFinder
Checks target validity.

### BFSPathFinder
Uses Breadth-First Search to determine whether a path exists.

### BFSPathTracker
Generates and reconstructs the shortest path.

### Score
Manages scoring throughout the simulation.

### SaveManager
Saves the final score to a text file.

---

## Algorithms Used

### Breadth-First Search (BFS)

BFS explores neighboring cells level-by-level until the target is found.

The algorithm:
1. Starts from the robot location.
2. Explores valid neighboring cells.
3. Avoids obstacles and visited cells.
4. Stops when the target is reached.

This guarantees the shortest path in an unweighted grid.

---

## AI Behaviour

The robot demonstrates autonomous behaviour by:

- Analyzing its environment
- Detecting obstacles
- Searching for valid paths
- Following the generated route automatically

The robot does not require user input after the simulation begins.

---

## How To Run

1. Open the project in NetBeans or another Java IDE.
2. Compile the project.
3. Run the `RescueBot.java` file.
4. Observe the robot navigate the grid.
5. Check the generated `score.txt` file for saved results.

---

## Example Output

```text
=== RESCUE BOT ===

R . . . .
. # # # .
. . . # .
. # . . .
. . . . T

Path exists: true

Rescue Bot reached the target!

Game saved!
```

---

## Future Improvements

- Recursive DFS implementation
- Sorting leaderboard scores
- Graphical User Interface (GUI)
- Multiple rescue targets
- Larger maps
- Dynamic obstacle generation

---

## Author

Maisie

High School Computer Science Project
