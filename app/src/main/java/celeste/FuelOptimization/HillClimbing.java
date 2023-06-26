package celeste.FuelOptimization;

import celeste.Interface.IVector3;
import celeste.Simulator.SolarSystem;
import celeste.Simulator.State;
import celeste.Simulator.Vector3;
import celeste.Simulator.CelestialBodies.CelestialBody;

public class HillClimbing {
    private final static int TITAN_RADIUS = 2575;
    /* Max orbit height of the orbit around Titan */
    private final static int MAX_ORBIT_HEIGHT = 5000;

    /* Marks the end for Hill Climbing calculations */
    private final static int MAX_TOLERANCE = TITAN_RADIUS + MAX_ORBIT_HEIGHT;

    /* Starting guess velocities for the hill climbing algorithm */
    private static double startX;
    private static double startY;
    private static double startZ;

    public static void main(String[] args) {
        /* This loads starting values from Values.txt file */
        new SolarSystem();
        startX = CelestialBody.spaceProbe.veloX;
        startY = CelestialBody.spaceProbe.veloY;
        startZ = CelestialBody.spaceProbe.veloZ;

        climb();
    }

    /*
     * This function is used to find the best velocity for the space probe. It uses
     * the hill climbing algorithm to find the best velocity.
     */
    private static void climb() {
        double funcValue = tryVelocity(startX, startY, startZ);

        /*
         * We don't ever change the X values, as it is set by us and we want to find the
         * best corresponding Y and Z values
         */
        double currX = startX;
        double currY = startY;
        double currZ = startZ;

        double stepSizeY = 3;
        double stepSizeZ = 1;

        while (true) {
            /* Adjust the y values */
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

            /* Adjust the z values */
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
    }

    /*
     * @param x: the x component of the velocity
     * 
     * @param y: the y component of the velocity
     * 
     * @param z: the z component of the velocity
     * 
     * @return the closest distance between Titan and the space probe
     * 
     * This function is used to calculate the closest distance between Titan and the
     * space probe with the given velocity. It is used to find the best velocity for
     * the space probe.
     */
    private static double tryVelocity(double x, double y, double z) {
        SolarSystem solarSystem = new SolarSystem();

        IVector3 velocity = new Vector3();
        velocity.setX(x);
        velocity.setY(y);
        velocity.setZ(z);
        State[] states = solarSystem.initialProcessOptimization(velocity);

        return findClosestPoint(states);
    }

    /*
     * @param states: the states of the space probe
     * 
     * @return the closest distance between Titan and the space probe
     * 
     * This function is used to find the closest distance between Titan and the
     * space probe.
     */
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