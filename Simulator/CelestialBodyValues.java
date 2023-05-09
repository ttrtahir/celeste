package Simulator;

public class CelestialBodyValues {
    public CelestialBody[] celestialBodies = new CelestialBody[12];

    public CelestialBodyValues() {
        for (int i = 0; i < Values.positions.length; i++) {
            celestialBodies[i] = new CelestialBody(Values.NAMES[i]);

            celestialBodies[i].mass = Values.mass[i][0];

            celestialBodies[i].posX = Values.positions[i][0];
            celestialBodies[i].posY = Values.positions[i][1];
            celestialBodies[i].posZ = Values.positions[i][2];

            celestialBodies[i].veloX = Values.velocity[i][0];
            celestialBodies[i].veloY = Values.velocity[i][1];
            celestialBodies[i].veloZ = Values.velocity[i][2];

            celestialBodies[i].color = Values.COLORS[i];
            celestialBodies[i].size = Values.SIZES[i];
            celestialBodies[i].name = Values.NAMES[i];

            celestialBodies[i].createVectors();
        }
    }
}
