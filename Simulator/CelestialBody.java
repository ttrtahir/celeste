package Simulator;

import java.awt.Color;

import Interface.IVector3;

public class CelestialBody {
    public String name;
    public Color color;
    public int size;

    public int step;

    public double mass;
    public double gravity;

    // velocity
    public double veloX;
    public double veloY;
    public double veloZ;

    // position
    public double posX;
    public double posY;
    public double posZ;

    // array to store all celestial bodies
    public static CelestialBody[] celestialBodies;

    // vectors
    public IVector3 posVec;
    public IVector3 veloVec;
    public IVector3 accelVec;

    public CelestialBody(String celestialName) {
        name = celestialName;
        step = 0;

    }

    public void createVectors() {
        posVec = new Vector3(posX, posY, posZ);
        veloVec = new Vector3(veloX, veloY, veloZ);
        accelVec = new Vector3(0, 0, 0);
    }

    public double getMass() {
        return mass;
    }

    // Needed for the genetic algorithm
    public void setVeloVec(double veloX) {
        this.veloX = veloX;
    }
    public void setPosVec(IVector3 posVec) {
        this.posVec = posVec;
    }
}
