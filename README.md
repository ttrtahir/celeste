# Mission to Titan (Group 04)

Michal Pavlíček, Yusuf Aydin, Paloma Joon, Marian Oboroceanu

## How to run

1. Open Terminal in the root directory of the project and run "java -jar .\app\build\libs\app.jar". This will start the GUI. (This version of the code does not contain orbitting around Titan and the return to Earth. This can be found in Phase II code)
2. Run src/main/java/celeste/GUI/Main.java to start the simulation.
   Requires correct Java and Gradle versions (tested on Gradle 8.1 and Java 18).

## Explanations

### Physics

The main file for physics is Simulator/SolarSystem.java. Everything follows from this file.
<br />
It loads the data from FileReader/Values.txt, calculates all the states of the solar system using Simulator/Solvers/..., applies the thrusts in Simulator/CelestialBodies/Engine.java and also calculates the fuel usage there.

### GUI

The key component of GUI is Drawables/Drawable.java, which is the interface for all drawable objects. It has a method draw(Graphics g) which is called by the GUI to draw any object, such as Planet, Trajectory, Text...

### Genetic Algorithm

Run Simulator/Genetic/GeneticAlgorithm.java to start the genetic algorithm. Values can be tweaked to your liking.

### Hill Climbing

Run FuelOptimization/HillClimbing.java to start the hill climbing algorithm. Values can be tweaked to your liking.
