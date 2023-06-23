package celeste.FuelOptimization;

import celeste.Interface.IVector3;
import celeste.Simulator.SolarSystem;
import celeste.Simulator.State;
import celeste.Simulator.Vector3;

public class HillClimbing {
    private static double startX = 44.46056868609268;
    private static double startY = -1.6266418240351048;
    private static double startZ = -3.294321300438615;

    public static void main(String[] args) {
        SolarSystem solarSystem = new SolarSystem();

        IVector3 velocity = new Vector3();
        velocity.setX(startX);
        velocity.setY(startY);
        velocity.setZ(startZ);
        State[] states = solarSystem.initialProcessOptimization(velocity);

        /* calculate closest the probe (index 11) has been to Titan (index 8) */
        System.out
                .println("Closest distance between Titan and probe: " + findClosestPoint(states));

        System.out.println("Best X: " + findBestX());
        System.out.println("Best Y: " + findBestY());
        System.out.println("Best Z: " + findBestZ());
    }

    private static double findBestX() {
        final double LEARNING_RATE = 0.00000001;
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

            System.out.println(distanceNew);

            if (distanceNew > distanceOld) {
                currX = oldX;
                break;
            }

            i--;
        }

        return currX;
    }

    private static double findBestY() {
        final double LEARNING_RATE = 0.00001;
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
            currY += LEARNING_RATE;
            oldY = temp;

            velocity.setY(currY);

            statesOld = statesNew;
            statesNew = solarSystem.initialProcessOptimization(velocity);
            System.out.println(distanceNew);

            distanceOld = distanceNew;
            distanceNew = findClosestPoint(statesNew);

            if (distanceNew > distanceOld) {
                currY = oldY;
                break;
            }

            i--;
        }

        return currY;
    }

    private static double findBestZ() {
        final double LEARNING_RATE = 0.00001;
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
            System.out.println(distanceNew);

            distanceOld = distanceNew;
            distanceNew = findClosestPoint(statesNew);

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

            if (distanceEuclideanNew > distanceEuclideanOld) {
                break;
            }
            if (distanceEuclideanNew < minDistanceBetweenTitanAndSpaceProbe) {
                minDistanceBetweenTitanAndSpaceProbe = distanceEuclideanNew;
            }
            distanceEuclideanOld = distanceEuclideanNew;

            i += 1;
        }

        return minDistanceBetweenTitanAndSpaceProbe;
    }
}