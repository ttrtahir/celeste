# Mission to Titan (Group 04)

Michal Pavlíček, Yusuf Aydin, Thu Vo, Paloma Joon, Marian Oboroceanu

## How to run

Run GUI/Main.java to start the simulation.

## Explanations

### Physics

The main file for physics is Simulator/SolarSystem.java. Everything follows from this file.
<br />
It loads the data from FileReader/Values.txt, calculates all the states of the solar system using Simulator/Solvers/..., applies the thrusts in Simulator/CelestialBodies/Engine.java and also calculates the fuel usage there.

### GUI

The key component of GUI is Drawables/Drawable.java, which is the interface for all drawable objects. It has a method draw(Graphics g) which is called by the GUI to draw any object, such as Planet, Trajectory, Text...

### Genetic Algorithm

TODO

### Tests

TODO
