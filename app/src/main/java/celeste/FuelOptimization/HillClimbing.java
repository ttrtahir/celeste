package celeste.FuelOptimization;

import celeste.Interface.IVector3;
import celeste.Simulator.SolarSystem;
import celeste.Simulator.State;
import celeste.Simulator.Vector3;
import celeste.Simulator.CelestialBodies.CelestialBody;

public class HillClimbing {
    private final static int TITAN_RADIUS = 2575;
    private final static int MAX_ORBIT_HEIGHT = 5000;

    private final static int MAX_TOLERANCE = TITAN_RADIUS + MAX_ORBIT_HEIGHT;

    private static double startX;
    private static double startY;
    private static double startZ;

    public static void main(String[] args) {
        new SolarSystem();
        startX = CelestialBody.spaceProbe.veloX;
        startY = CelestialBody.spaceProbe.veloY;
        startZ = CelestialBody.spaceProbe.veloZ;

        climb();
    }

    private static double climb() {
        System.out.println(FuelConsumption.calculateFuelConsumed(new Vector3(0, 0, 0),
                new Vector3(40.0, -34.54183475971889, -3.2951223808465544)));
        /* We want to find the minimum in func */
        double initialGuessX = startX;
        double initialGuessY = startY;
        double initialGuessZ = startZ;

        double funcValue = tryVelocity(initialGuessX, initialGuessY, initialGuessZ);

        double currX = initialGuessX;
        double currY = initialGuessY;
        double currZ = initialGuessZ;

        double stepSizeY = 3;
        double stepSizeZ = 1;

        while (true) {
            double right = tryVelocity(currX, currY + stepSizeY, currZ);
            double left = tryVelocity(currX, currY - stepSizeY, currZ);
            if (right < funcValue) {
                currY += stepSizeY;
                funcValue = right;
            } else if (left < funcValue) {
                currY -= stepSizeY;
                funcValue = left;
            } else {
                stepSizeY /= 1.2; // 1.2 is a random number that after some
                                  // testing seemed reasonable
            }
            if (funcValue < MAX_TOLERANCE) {
                break;
            }

            right = tryVelocity(currX, currY, currZ + stepSizeZ);
            left = tryVelocity(currX, currY, currZ - stepSizeZ);
            if (right < funcValue) {
                currZ += stepSizeZ;
                funcValue = right;
            } else if (left < funcValue) {
                currZ -= stepSizeZ;
                funcValue = left;
            } else {
                stepSizeZ /= 1.2;
            }
            if (funcValue < MAX_TOLERANCE) {
                break;
            }
        }
        System.out.println(currX + " " + currY + " " + currZ);
        System.out.println("Fuel consumed: "
                + FuelConsumption.calculateFuelConsumed(new Vector3(0, 0, 0), new Vector3(currX, currY, currZ)));

        return currX;

    }

    private static double tryVelocity(double x, double y, double z) {
        SolarSystem solarSystem = new SolarSystem();

        IVector3 velocity = new Vector3();
        velocity.setX(x);
        velocity.setY(y);
        velocity.setZ(z);
        State[] states = solarSystem.initialProcessOptimization(velocity);

        System.out.println("Closest point: " + findClosestPoint(states));

        return findClosestPoint(states);
    }

    private static double findClosestPoint(State[] states) {
        double minDistanceBetweenTitanAndSpaceProbe = 1e30;
        double distanceEuclideanNew = 1e30;
        int i = 0;
        while (i < states.length) {
            IVector3 posTitan = (IVector3) states[i].state[8][0];
            IVector3 posProbe = (IVector3) states[i].state[11][0];
            distanceEuclideanNew = posProbe.euclideanDist(posTitan);

            if (distanceEuclideanNew < minDistanceBetweenTitanAndSpaceProbe) {
                minDistanceBetweenTitanAndSpaceProbe = distanceEuclideanNew;
            }

            i += 1;
        }

        return minDistanceBetweenTitanAndSpaceProbe;
    }
}