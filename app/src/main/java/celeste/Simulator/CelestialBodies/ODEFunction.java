package celeste.Simulator.CelestialBodies;

import celeste.Interface.IAccelerationRate;
import celeste.Interface.IODEFunction;
import celeste.Interface.IState;
import celeste.Simulator.State;
import celeste.Simulator.Vector3;

/**
 * A class contains a method for Newton's gravitational calculations
 */
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

        for (int i = 0; i < nBodies; i++) {
            Vector3 velocity = (Vector3) ((State) y).getVelocity(i);
            acceleration.addVelocity(i, velocity);
        }

        for (int i = 0; i < nBodies; i++) {
            double distance = 0;
            for (int j = 0; j < nBodies; j++) {
                if (i != j) {
                    // calculate the distance
                    distance = Math.pow(((State) y).getPosition(i).euclideanDist(((State) y).getPosition(j)), 3);
                    Vector3 positionalDifference = (Vector3) ((((State) y).getPosition(j)
                            .subtract(((State) y).getPosition(i))));

                    // a(i) = G * m(j) * pos(i) - pos(j) / (euler distance(pos(i) - pos(j))) ^ 3
                    Vector3 tempAcc = (Vector3) (positionalDifference
                            .multiply(G * CelestialBody.celestialBodies[j].getMass() / distance));

                    // add the temporary acceleration to total acceleration of a planet
                    acceleration.addAcceleration(i, tempAcc);
                }
            }
        }
        return acceleration;
    }

    /* Update the forces acting on the space probe */
    @Override
    public IAccelerationRate callProbe(double t, IState y) {
        acceleration.initialize(1);

        Vector3 velocity = (Vector3) ((State) y).getVelocity(11);
        acceleration.addVelocity(0, velocity);

        double distance = 0;
        for (int j = 0; j < nBodies; j++) {
            // calculate the distance
            distance = Math.pow(((State) y).getPosition(11).euclideanDist(((State) y).getPosition(j)), 3);
            Vector3 positionalDifference = (Vector3) ((((State) y).getPosition(j)
                    .subtract(((State) y).getPosition(11))));

            // a(i) = G * m(j) * pos(i) - pos(j) / (euler distance(pos(i) - pos(j))) ^ 3
            Vector3 tempAcc = (Vector3) (positionalDifference
                    .multiply(G * CelestialBody.celestialBodies[j].getMass() / distance));

            // add the temporary acceleration to total acceleration of a planet
            acceleration.addAcceleration(0, tempAcc);
        }

        return acceleration;
    }
}
