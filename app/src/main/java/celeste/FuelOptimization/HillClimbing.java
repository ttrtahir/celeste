package celeste.FuelOptimization;

import celeste.Interface.IVector3;
import celeste.Simulator.SolarSystem;
import celeste.Simulator.State;
import celeste.Simulator.Vector3;
import celeste.Simulator.CelestialBodies.CelestialBody;

public class HillClimbing {
    private final static int TITAN_RADIUS = 2575;
    private final static int MAX_ORBIT_HEIGHT = 5000;
    private final static int MIN_ORBIT_HEIGHT = 0;

    private final static int MAX_TOLERANCE = TITAN_RADIUS + MAX_ORBIT_HEIGHT;
    private final static int MIN_TOLERANCE = TITAN_RADIUS + MIN_ORBIT_HEIGHT;

    private static double startX;
    private static double startY;
    private static double startZ;

    public static void main(String[] args) {
        SolarSystem solarSystem = new SolarSystem();
        startX = CelestialBody.spaceProbe.veloX;
        startY = CelestialBody.spaceProbe.veloY;
        startZ = CelestialBody.spaceProbe.veloZ;

        climb();
    }

    private static double climb() {
        /* We want to find the minimum in func */
        double initialGuessX = startX;
        double initialGuessY = startY;
        double initialGuessZ = startZ;

        double oldFuncValue = 0;
        double funcValue = tryVelocity(initialGuessX, initialGuessY, initialGuessZ);

        double currX = initialGuessX;
        double currY = initialGuessY;
        double currZ = initialGuessZ;

        double STEP_SIZE_SPEED = 5;
        double SSX = 8 * STEP_SIZE_SPEED;
        double SSY = 0.1 * STEP_SIZE_SPEED;
        double SSZ = 0.01 * STEP_SIZE_SPEED;

        double ERROR = 10;

        double stepSizeX = SSX;
        double stepSizeY = SSY;
        double stepSizeZ = SSZ;

        int iterations = 1;

        outerloop: while (true) {
            oldFuncValue = 0;
            funcValue = tryVelocity(currX, currY, currZ);
            stepSizeX = SSX / Math.pow(iterations, STEP_SIZE_SPEED);
            while (Math.abs(funcValue - oldFuncValue) > ERROR) {
                double right = tryVelocity(currX + stepSizeX, currY, currZ);
                double left = tryVelocity(currX - stepSizeX, currY, currZ);
                if (right < funcValue) {
                    currX += stepSizeX;
                    oldFuncValue = funcValue;
                    funcValue = right;
                } else if (left < funcValue) {
                    currX -= stepSizeX;
                    oldFuncValue = funcValue;
                    funcValue = left;
                } else {
                    stepSizeX /= 2;
                }
                if (funcValue < MAX_TOLERANCE && funcValue > MIN_TOLERANCE) {
                    break outerloop;
                }
            }

            oldFuncValue = 0;
            funcValue = tryVelocity(currX, currY, currZ);
            stepSizeY = SSY / Math.pow(iterations, STEP_SIZE_SPEED);
            System.out.println("---> Best X found: " + currX);
            while (Math.abs(funcValue - oldFuncValue) > ERROR) {
                double right = tryVelocity(currX, currY + stepSizeY, currZ);
                double left = tryVelocity(currX, currY - stepSizeY, currZ);
                if (right < funcValue) {
                    currY += stepSizeY;
                    oldFuncValue = funcValue;
                    funcValue = right;
                } else if (left < funcValue) {
                    currY -= stepSizeY;
                    oldFuncValue = funcValue;
                    funcValue = left;
                } else {
                    stepSizeY /= 2;
                }
                if (funcValue < MAX_TOLERANCE && funcValue > MIN_TOLERANCE) {
                    break outerloop;
                }
            }

            oldFuncValue = 0;
            funcValue = tryVelocity(currX, currY, currZ);
            stepSizeZ = SSZ / Math.pow(iterations, STEP_SIZE_SPEED);
            System.out.println("---> Best Y found: " + currY);
            while (Math.abs(funcValue - oldFuncValue) > ERROR) {
                double right = tryVelocity(currX, currY, currZ + stepSizeZ);
                double left = tryVelocity(currX, currY, currZ - stepSizeZ);
                if (right < funcValue) {
                    currZ += stepSizeZ;
                    oldFuncValue = funcValue;
                    funcValue = right;
                } else if (left < funcValue) {
                    currZ -= stepSizeZ;
                    oldFuncValue = funcValue;
                    funcValue = left;
                } else {
                    stepSizeZ /= 2;
                }
                if (funcValue < MAX_TOLERANCE && funcValue > MIN_TOLERANCE) {
                    break outerloop;
                }
            }

            System.out.println("---> Best Z found: " + currZ);
            iterations++;
        }
        System.out.println(currX + " " + currY + " " + currZ);

        return currX;
    }
    /*
     * { 34.04488999999986, -37.16743182403512, -3.7812213004386046 }
     */

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

    private static double findBestX() {
        final double LEARNING_RATE = 1;
        SolarSystem solarSystem = new SolarSystem();

        double oldX = startX - LEARNING_RATE;

        IVector3 velocity = new Vector3();
        double currX = startX;

        velocity.setX(oldX);
        velocity.setY(startY);
        velocity.setZ(startZ);
        State[] statesOld = solarSystem.initialProcessOptimization(velocity);

        velocity.setX(currX);
        State[] statesNew = solarSystem.initialProcessOptimization(velocity);

        double distanceOld = findClosestPoint(statesOld);
        double distanceNew = findClosestPoint(statesNew);

        int i = 1000;
        while (i > 0) {
            double temp = currX;
            currX += LEARNING_RATE;
            oldX = temp;

            velocity.setX(currX);

            statesOld = statesNew;
            statesNew = solarSystem.initialProcessOptimization(velocity);

            distanceOld = distanceNew;
            distanceNew = findClosestPoint(statesNew);
            System.out.println("Distance: " + distanceNew);

            if (distanceNew > distanceOld) {
                currX = oldX;
                break;
            }

            i--;
        }

        return currX;
    }

    private static double findBestY() {
        final double LEARNING_RATE = 1;
        SolarSystem solarSystem = new SolarSystem();

        double oldY = startY - LEARNING_RATE;

        IVector3 velocity = new Vector3();
        double currY = startY;

        velocity.setX(startX);
        velocity.setY(oldY);
        velocity.setZ(startZ);
        State[] statesOld = solarSystem.initialProcessOptimization(velocity);

        velocity.setY(currY);
        State[] statesNew = solarSystem.initialProcessOptimization(velocity);

        double distanceOld = findClosestPoint(statesOld);
        double distanceNew = findClosestPoint(statesNew);

        int i = 1000;
        while (i > 0) {
            double temp = currY;
            currY -= LEARNING_RATE;
            oldY = temp;

            velocity.setY(currY);

            statesOld = statesNew;
            statesNew = solarSystem.initialProcessOptimization(velocity);

            distanceOld = distanceNew;
            distanceNew = findClosestPoint(statesNew);
            System.out.println("Distance: " + distanceNew);

            if (distanceNew > distanceOld) {
                currY = oldY;
                break;
            }

            i--;
        }

        return currY;
    }

    private static double findBestZ() {
        final double LEARNING_RATE = 0.1;
        SolarSystem solarSystem = new SolarSystem();

        double oldZ = startZ - LEARNING_RATE;

        IVector3 velocity = new Vector3();
        double currZ = startZ;

        velocity.setX(startX);
        velocity.setY(startY);
        velocity.setZ(oldZ);
        State[] statesOld = solarSystem.initialProcessOptimization(velocity);

        velocity.setZ(currZ);
        State[] statesNew = solarSystem.initialProcessOptimization(velocity);

        double distanceOld = findClosestPoint(statesOld);
        double distanceNew = findClosestPoint(statesNew);

        int i = 1000;
        while (i > 0) {
            double temp = currZ;
            currZ -= LEARNING_RATE;
            oldZ = temp;

            velocity.setZ(currZ);

            statesOld = statesNew;
            statesNew = solarSystem.initialProcessOptimization(velocity);

            distanceOld = distanceNew;
            distanceNew = findClosestPoint(statesNew);
            System.out.println("Distance: " + distanceNew);

            if (distanceNew > distanceOld) {
                currZ = oldZ;
                break;
            }

            i--;
        }

        return currZ;
    }

    private static double findClosestPoint(State[] states) {
        double minDistanceBetweenTitanAndSpaceProbe = 1e30;
        double distanceEuclideanOld = 1e29;
        double distanceEuclideanNew = 1e30;
        int i = 0;
        while (i < states.length) {
            IVector3 posTitan = (IVector3) states[i].state[8][0];
            IVector3 posProbe = (IVector3) states[i].state[11][0];
            distanceEuclideanNew = posProbe.euclideanDist(posTitan);

            if (distanceEuclideanNew < minDistanceBetweenTitanAndSpaceProbe) {
                minDistanceBetweenTitanAndSpaceProbe = distanceEuclideanNew;
            }
            distanceEuclideanOld = distanceEuclideanNew;

            i += 1;
        }

        return minDistanceBetweenTitanAndSpaceProbe;
    }

    private static void prevCode() {
        SolarSystem solarSystem = new SolarSystem();

        startX = CelestialBody.spaceProbe.posX;
        startY = CelestialBody.spaceProbe.posY;
        startZ = CelestialBody.spaceProbe.posZ;

        IVector3 velocity = new Vector3();
        velocity.setX(startX);
        velocity.setY(startY);
        velocity.setZ(startZ);
        State[] states = solarSystem.initialProcessOptimization(velocity);

        /* calculate closest the probe (index 11) has been to Titan (index 8) */
        System.out
                .println("Closest distance between Titan and probe: " + findClosestPoint(states));

        // System.out.println("Best X: " + findBestX());
        System.out.println("Best Y: " + findBestY());
        System.out.println("Best Z: " + findBestZ());
    }
}