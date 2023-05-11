package Simulator;

import Interface.IAccelerationRate;
import Interface.IODEFunction;
import Interface.IState;

public class ODEFunction implements IODEFunction {
    // Gravitational constant
    public static final double G = 6.6743E-20;
    public static int nBodies = CelestialBody.celestialBodies.length;
    public AccelerationRate acceleration = new AccelerationRate();

    /**
     * F = G* mi *mj * (pos_i - pos_j)/|pos_i - pos_j|^3
     *
     * @param t
     * @param y state of the Solar System
     * @return acceleration for each planet
     */

    @Override
    public IAccelerationRate call(double t, IState y) {
        acceleration.initialize(nBodies);
        System.out.println("acceleration before update " + acceleration.toString());
        // traverse all celestial bodies
        for (int i = 0; i < nBodies; i++) {
            double distance = 0;
            for (int j = 0; j < nBodies; j++) {
                if (i != j) {
                    // calculate the distance
                    distance = Math.pow(((State) y).getPosition(i).euclideanDist(((State) y).getPosition(j)), 3);
                    Vector3 tempAcc = (Vector3) (((State) y).getPosition(j).subtract(((State) y).getPosition(i)))
                            .multiply(1 / distance);
                    // add the temporary acceleration to total acceleration of a planet
                    acceleration.addAcceleration(i, tempAcc);
                }
            }
        }
        System.out.println("Acceleration after update " + acceleration.toString());
        return acceleration;
    }

}
